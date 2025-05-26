FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY build/libs/passportbackend-*-SNAPSHOT.jar /app/application.jar
COPY deploy/application.yaml /app/config/
VOLUME /app/config
ENTRYPOINT java -jar application.jar --spring.config.location=config/application.yaml