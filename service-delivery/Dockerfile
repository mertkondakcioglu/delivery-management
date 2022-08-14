FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} delivery.jar
ENTRYPOINT ["java", "-jar", "delivery.jar"]
