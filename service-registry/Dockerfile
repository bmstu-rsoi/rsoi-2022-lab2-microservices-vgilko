FROM eclipse-temurin:17-jre-focal
EXPOSE 8761
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]