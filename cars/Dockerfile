FROM eclipse-temurin:17-jre-focal
EXPOSE 8070
ARG JAR_FILE=cars-impl/target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]