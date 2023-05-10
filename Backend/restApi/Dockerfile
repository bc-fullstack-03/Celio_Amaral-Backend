FROM eclipse-temurin:17-jdk-alpine
ADD target/restApi-*.jar restApi.jar
ENTRYPOINT ["java", "-jar", "/restApi.jar"]