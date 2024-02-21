FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the Docker image
WORKDIR /app

# Copy the Spring Boot JAR file into the Docker image
COPY build/libs/*.jar app.jar

# Expose port 8072
EXPOSE 8072

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
