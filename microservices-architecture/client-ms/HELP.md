# Kafka
To add Kafka to this project:
1. Update pom.xml with maven `spring-kafka` and `kafka-client` dependencies
2. Create a `KafkaConfiguration` class
3. In order to send event to Kafka, inject a `KafkaTemplate` to `ClientService` and call the template to send the event
   1. Add Kafka properties to the `application.properties` file
   2. Add testes for Kafka integration
4. `// Todo:`
5. Commands to validate Kafka
   * Create topic: 
     * `kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic client-topic`
     * `docker-compose exec kafka kafka-topics --create --topic client-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1`
   * List topics
     * ``kafka-topics.sh --list --bootstrap-server localhost:9092``
     * `docker-compose exec kafka kafka-topics --list --bootstrap-server localhost:9092`

   adjust properties
       `spring.kafka.bootstrap-servers=localhost:9092`

6. 


**Docker Compose** file defines two services:

zookeeper
: A Zookeeper container for managing the Kafka cluster.

kafka
: A Kafka container that depends on the Zookeeper service and is configured to advertise its listeners on port 9092.

Start the Kafka Cluster 2.1. Open a terminal or command prompt. 2.2. Navigate to the directory containing the
docker-compose.yml
file. 2.3. R0un the following command to start the Kafka cluster:

`docker-compose up -d`


# Read Me First
The following was discovered as part of building this project:

* Spring Cloud Gateway requires Spring WebFlux, your choice of Spring Web has been replaced accordingly.
* The original package name 'com.javaday.client-ms' is invalid and this project uses 'com.javaday.clientms' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.12-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.12-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Gateway](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/2.7.12-SNAPSHOT/reference/htmlsingle/#web.reactive)

### Guides
The following guides illustrate how to use some features concretely:

* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)
* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)

