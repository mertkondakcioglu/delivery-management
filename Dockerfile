FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} delivery-management.jar
ENTRYPOINT ["java", "-jar", "delivery-management.jar"]
