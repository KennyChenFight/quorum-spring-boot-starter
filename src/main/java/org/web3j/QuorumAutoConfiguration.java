package org.web3j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.ipc.UnixIpcService;
import org.web3j.protocol.ipc.WindowsIpcService;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.quorum.Quorum;

import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;


@Configuration
@ConditionalOnClass(Quorum.class)
@EnableConfigurationProperties(QuorumProperties.class)
public class QuorumAutoConfiguration {

    private static Log log = LogFactory.getLog(QuorumAutoConfiguration.class);

    @Autowired
    private QuorumProperties quorumProperties;

    @Bean
    @ConditionalOnMissingBean
    public Quorum quorum() throws URISyntaxException, ConnectException {
        String clientAddress = quorumProperties.getClientAddress() == null ? "http://localhost:8545" : quorumProperties.getClientAddress();
        Quorum quorum = Quorum.build(buildService(clientAddress));
        log.info("Building quorum service success for endpoint: " + clientAddress);
        return quorum;
    }

    private Web3jService buildService(String clientAddress) throws URISyntaxException, ConnectException {
        Web3jService web3jService;

        if (clientAddress.contains("infura.io")) {
            web3jService = new HttpService(clientAddress);
        } else if (clientAddress.contains("http")) {
            web3jService = new HttpService(clientAddress);
        } else if (clientAddress.contains("ws")) {
            try {
                WebSocketClient webSocketClient = new WebSocketClient(new URI(clientAddress));
                WebSocketService webSocketService = new WebSocketService(webSocketClient, false);
                webSocketService.connect();
                web3jService = webSocketService;
                return web3jService;
            } catch (Exception e) {
                log.error("websocket endpoint: " + clientAddress + " is not work");
                throw e;
            }
        }
        else if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            web3jService = new WindowsIpcService(clientAddress);
        } else {
            web3jService = new UnixIpcService(clientAddress);
        }

        return web3jService;
    }
}
