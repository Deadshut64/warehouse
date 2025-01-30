FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/warehouse-1.0.1-SNAPSHOT.jar

WORKDIR /opt/servises

COPY ${JAR_FILE} warehouse.jar

ENTRYPOINT ["java","-jar","warehouse.jar"]