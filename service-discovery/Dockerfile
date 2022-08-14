FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} discovery.jar
ENTRYPOINT ["java", "-jar", "discovery.jar"]
