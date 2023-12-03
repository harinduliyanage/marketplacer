FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/marketplacer-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "marketplacer-0.0.1-SNAPSHOT.jar"]
