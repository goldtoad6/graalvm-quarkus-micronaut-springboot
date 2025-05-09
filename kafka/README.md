# graalvm-quarkus-micronaut-springboot
## `> kafka`

In this category, we have implemented three versions of `kafka-producer - kafka-consumer` applications using `Quarkus`, `Micronaut` and `Spring Boot` Frameworks.

## Applications

- ### [quarkus-kafka](https://github.com/ivangfr/graalvm-quarkus-micronaut-springboot/tree/master/kafka/quarkus-kafka#graalvm-quarkus-micronaut-springboot)
- ### [micronaut-kafka](https://github.com/ivangfr/graalvm-quarkus-micronaut-springboot/tree/master/kafka/micronaut-kafka#graalvm-quarkus-micronaut-springboot)
- ### [springboot-kafka](https://github.com/ivangfr/graalvm-quarkus-micronaut-springboot/tree/master/kafka/springboot-kafka#graalvm-quarkus-micronaut-springboot)

## Dependency version

| Framework   | Confluent Platform | Apache Kafka |
|-------------|--------------------|--------------|
| Quarkus     | 7.9.x              | 3.9.0        |
| Micronaut   | 7.8.x              | 3.8.1        |
| Spring Boot | 7.8.x              | 3.8.1        |

## Start Environment

- Open a terminal and navigate to the `graalvm-quarkus-micronaut-springboot/kafka` folder.

- Run the command:
  ```bash
  podman compose up -d
  ```

- Wait for all containers to be up and running. To check it, run:
  ```bash
  podman compose ps
  ```

## Shutdown

- In a terminal, make sure you are in the `graalvm-quarkus-micronaut-springboot/kafka` folder.

- To stop and remove compose containers, networks and volumes, run:
  ```bash
  podman compose down -v
  ```

## Useful links

- **Kafdrop**

  `Kafdrop` can be accessed at http://localhost:9001
