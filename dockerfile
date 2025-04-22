# Use the official OpenJDK 11 image as the base
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file from your local machine to the container
COPY target/Market-Management-System-0.0.1-SNAPSHOT.jar /app/Market-Management-System-0.0.1-SNAPSHOT.jar

# Expose port 8081 inside the container
EXPOSE 8081

# Run the Spring Boot application
CMD ["java", "-jar", "Market-Management-System-0.0.1-SNAPSHOT.jar"]
