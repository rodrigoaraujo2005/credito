# Multi-stage build for Spring Boot application

# Build stage
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
# Download dependencies separately to leverage Docker cache
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create a non-root user to run the application
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup
USER appuser

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Environment variables that can be overridden
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/creditdb \
    SPRING_DATASOURCE_USERNAME=credituser \
    SPRING_DATASOURCE_PASSWORD=creditpass \
    SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
    SERVER_PORT=8080

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]