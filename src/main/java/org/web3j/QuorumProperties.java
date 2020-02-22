package org.web3j;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "org.web3j.quorum")
public class QuorumProperties {
    private String clientAddress;

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
}
