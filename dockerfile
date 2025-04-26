# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the correct JAR built by Maven
COPY target/studentsystem-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8282
EXPOSE 8282

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
