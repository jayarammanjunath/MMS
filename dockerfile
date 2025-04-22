# Use OpenJDK 17 from Docker Hub (this version includes JDK)
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file from your local machine to the container
COPY target/Market-Management-System-0.0.1-SNAPSHOT.jar /app/Market-Management-System-0.0.1-SNAPSHOT.jar

# Expose port 8081 inside the container
EXPOSE 8081

# Run the Spring Boot application
CMD ["java", "-jar", "Market-Management-System-0.0.1-SNAPSHOT.jar"]
