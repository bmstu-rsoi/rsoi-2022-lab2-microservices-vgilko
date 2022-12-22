FROM eclipse-temurin:17-jre-focal
EXPOSE 8080
ARG JAR_FILE=gateway-impl/target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]