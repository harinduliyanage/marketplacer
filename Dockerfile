# Stage 1: Build the application
FROM gradle:8.2.1-jdk17 AS builder

# Environment varibales to stage 1
ARG DB_HOST
ENV DB_HOST $DB_HOST

ARG DB_NAME
ENV DB_NAME $DB_NAME

ARG DB_PWD
ENV DB_PWD $DB_PWD

ARG DB_USER
ENV DB_USER $DB_USER

ARG KEYCLOAK_BASE_URL
ENV KEYCLOAK_BASE_URL $KEYCLOAK_BASE_URL

ARG KEYCLOAK_TARGET_REALM
ENV KEYCLOAK_TARGET_REALM $KEYCLOAK_TARGET_REALM

ARG KEYCLOAK_REALM
ENV KEYCLOAK_REALM $KEYCLOAK_REALM

ARG S3_BUCKET_NAME
ENV S3_BUCKET_NAME $S3_BUCKET_NAME

ARG AWS_ACCESS_KEY
ENV AWS_ACCESS_KEY $AWS_ACCESS_KEY

ARG AWS_REGION
ENV AWS_REGION $AWS_REGION

ARG AWS_SECRET_KEY
ENV AWS_SECRET_KEY $AWS_SECRET_KEY

# Set the working directory
WORKDIR /app

# Copy only the build files and Gradle scripts
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Copy the application source code
COPY src /app/src

# Build the application
RUN gradle --no-daemon --console=plain build -x test

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Environment varibales to stage 2
ARG DB_HOST
ENV DB_HOST $DB_HOST

ARG DB_NAME
ENV DB_NAME $DB_NAME

ARG DB_PWD
ENV DB_PWD $DB_PWD

ARG DB_USER
ENV DB_USER $DB_USER

ARG KEYCLOAK_BASE_URL
ENV KEYCLOAK_BASE_URL $KEYCLOAK_BASE_URL

ARG KEYCLOAK_TARGET_REALM
ENV KEYCLOAK_TARGET_REALM $KEYCLOAK_TARGET_REALM

ARG KEYCLOAK_REALM
ENV KEYCLOAK_REALM $KEYCLOAK_REALM

ARG S3_BUCKET_NAME
ENV S3_BUCKET_NAME $S3_BUCKET_NAME

ARG AWS_ACCESS_KEY
ENV AWS_ACCESS_KEY $AWS_ACCESS_KEY

ARG AWS_REGION
ENV AWS_REGION $AWS_REGION

ARG AWS_SECRET_KEY
ENV AWS_SECRET_KEY $AWS_SECRET_KEY

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar


# Expose the port that the application will run on
EXPOSE 8089

# Specify the command to run on container startup
ENTRYPOINT ["java", "-jar", "app.jar"]

