
# Getting Started - Pismo Test 3.0

### Steps to configurate and start the app:

## Pré-requisites:
* Java 8
* Maven Installed in you machine.
* Docker Installed

## How to start the app:

* Import the project as Maven project in your IDE.
* Run Maven clean install by command line: mvn clean install
* Run from root directory the command  'docker compose up -d'  to create the database and start the redis service
* Configure the app to run WebApplication to start the server.

## Some informations:
* For documentation of the endpoints you can access the [Swagguer](http://localhost:8080/pismotest/swagger-ui/index.html)

* For health check you can access the [Actuator](http://localhost:8080/pismotest/actuator)

* For code coverage report you can access the index.hml under target/site/jacoco-resources

* To see more about the app check the wiki in the git repository.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.5/maven-plugin/reference/html/)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#web.reactive)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#data.nosql.redis)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.5/reference/htmlsingle/#actuator)
* [Sleuth](https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/htmlsingle/spring-cloud-sleuth.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)


### Urls -

* [Swagguer](http://localhost:8080/pismotest/swagger-ui/index.html)
* [Actuator](http://localhost:8080/pismotest/actuator)

