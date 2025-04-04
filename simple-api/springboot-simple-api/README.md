# graalvm-quarkus-micronaut-springboot
## `> simple-api > springboot-simple-api`

## Application

- ### springboot-simple-api

  [`Spring Boot`](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) Java Web application that exposes a simple REST API for greetings. [Spring Initializr](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.4.4&packaging=jar&jvmVersion=17&groupId=com.ivanfranchin&artifactId=springboot-simple-api&name=springboot-simple-api&description=Demo%20project%20for%20Spring%20Boot&packageName=com.ivanfranchin.springboot-simple-api&dependencies=webflux,actuator,validation,native)
  
  It has the following endpoints:
  ```text
  GET /api/greeting[?name=...]
  GET /actuator/health
  GET /actuator/metrics
  ```

## Running application

### Development Mode

- Open a terminal and navigate to the `graalvm-quarkus-micronaut-springboot/simple-api/springboot-simple-api` folder.

- Run the command below to start the application:
  ```bash
  ./mvnw clean spring-boot:run
  ```

- A simple test can be done by opening a new terminal and running:
  ```bash
  curl -i "localhost:8080/api/greeting?name=Ivan"
  ```

- To stop the application, press `Ctrl+C` in its terminal.

### Docker in JVM Mode

- In a terminal, make sure you are inside the `graalvm-quarkus-micronaut-springboot/simple-api/springboot-simple-api` folder.

- Clean the target folder:
  ```bash
  ./mvnw clean
  ```

- Run the script below to build the Docker image:
  ```bash
  ./build-docker-images.sh
  ```

- Run the following command to start the container:
  ```bash
  podman run --rm --name springboot-simple-api-jvm -p 9084:8080 \
    ivanfranchin/springboot-simple-api-jvm:latest
  ```

- A simple test can be done by opening a new terminal and running:
  ```bash
  curl -i "localhost:9084/api/greeting?name=Ivan"
  ```

- To stop and remove application container, press `Ctrl+C` in its terminal.

### Docker in Native Mode

- In a terminal, make sure you are inside the `graalvm-quarkus-micronaut-springboot/simple-api/springboot-simple-api` folder.

- Clean the target folder:
  ```bash
  ./mvnw clean
  ```

- Run the script below to build the Docker image:
  ```bash
  ./build-docker-images.sh native
  ```

- Run the following command to start the container:
  ```bash
  podman run --rm --name springboot-simple-api-native -p 9085:8080 \
    ivanfranchin/springboot-simple-api-native:latest
  ```

- A simple test can be done by opening a new terminal and running:
  ```bash
  curl -i "localhost:9085/api/greeting?name=Ivan"
  ```

- To stop and remove application container, press `Ctrl+C` in its terminal.
