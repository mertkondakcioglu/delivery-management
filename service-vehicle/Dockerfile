FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} vehicle.jar
ENTRYPOINT ["java", "-jar", "vehicle.jar"]
