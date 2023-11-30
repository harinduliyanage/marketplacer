FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/*.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/app.jar"]
