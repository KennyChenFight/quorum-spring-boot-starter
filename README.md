# quorum-spring-boot-starter
fork from https://github.com/A-Joshi/web3j-quorum-spring-boot-starter

## Getting started
I do not publish this dependency to central maven repository. So, you have to install jar in your computer.
To use, following instructions:
   Maven:
   <dependency>
       <groupId>org.web3j</groupId>
       <artifactId>web3j-quorum-spring-boot-starter</artifactId>
       <version>1.2.0</version>
   </dependency>
   Gradle:
   
   compile ('org.web3j:web3j-quorum-spring-boot-starter:1.2.0')
   Now Spring can inject web3j quorum instances for you where ever you need them:
   
   @Autowired
   private Quorum quorum;
   No additional configuration is required if you want to connect via HTTP to the default URL http://localhost:8545.
   
   Otherwise simply add the address of the endpoint in your application properties:
   
   # An infura endpoint
   web3j.quorum.client-address = https://morden.infura.io/
   
   # Or, an IPC endpoing
   web3j.quorum.client-address = /path/to/file.ipc
   Admin clients
   If you wish to make use of the Parity or Geth personal modules to manage accounts, enable the admin client:
   
   web3j.quorum.admin-client = true
   Then Spring can inject admin clients:
   
   @Autowired
   private Parity parity;
   Note: This is not required for transacting with web3j quorum.
   
   Further information
   For further information on web3j quorum, please refer to the web3j home page.
