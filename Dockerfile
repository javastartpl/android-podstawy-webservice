FROM openjdk:8-jre-slim
ARG JAR_FILE
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]