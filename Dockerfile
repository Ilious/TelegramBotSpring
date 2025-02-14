FROM openjdk:21-jdk-slim

COPY /build/libs/TelegramBotSpring-0.0.1-SNAPSHOT.jar /app/Main.jar
WORKDIR /app

ENTRYPOINT java -jar Main.jar