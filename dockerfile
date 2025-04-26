# Use OpenJDK 17 from Docker Hub (this version includes JDK)
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/studentsystem-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8282
EXPOSE 8282

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
