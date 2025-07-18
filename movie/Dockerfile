# jar 버전 지정
FROM openjdk:17-jdk-slim

WORKDIR /app

RUN mkdir -p upload

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]