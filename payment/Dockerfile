FROM eclipse-temurin:17-jre-focal
EXPOSE 8050
ARG JAR_FILE=payment-impl/target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]