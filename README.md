# quorum-spring-boot-starter
fork from https://github.com/A-Joshi/web3j-quorum-spring-boot-starter

## Getting started
I do not publish this dependency to central maven repository. So, you have to install jar in your computer.
To use, following instructions:  

1. install jar using maven

   ```bash
   git clone https://github.com/KennyChenFight/quorum-spring-boot-starter.git
   mvn clean
   mvn install
   ```

2. create a new Spring Boot Project and add maven dependency

   ```xml
   <dependency>
       <groupId>org.web3j</groupId>
       <artifactId>quorum-spring-boot-starter</artifactId>
       <version>4.0.6</version>
   </dependency>
   ```

3. add quorum properties

   ```bash
   # http service
   org.web3j.quorum.client-address=http://localhost:7545
   # infura service
   org.web3j.quorum.client-address=https://morden.infura.io/
   # websocket service
   org.web3j.quorum.client-address=ws://localhost:7545
   # ipc file for unix or windows
   org.web3j.quorum.client-address=/path/to/file.ipc
   ```

   just choose one to use. 

4. autowire **quorum** bean and get client version

   ```java
   @SpringBootApplication
   public class QuorumUserDaemonApplication implements CommandLineRunner {
   
       @Autowired
       private Quorum quorum;
   
       public static void main(String[] args) {
           SpringApplication.run(QuorumUserDaemonApplication.class, args);
       }
   
       @Override
       public void run(String... args) throws Exception {
           Web3ClientVersion web3ClientVersion = quorum.web3ClientVersion().send();
           String clientVersion = web3ClientVersion.getWeb3ClientVersion();
           System.out.println(clientVersion);
       }
   }
   ```

   if it did not connect, it will show exception.

## Problems

Because I use https://github.com/web3j/web3j-quorum library and package to spring boot starter, you can see this document to conquer problems.