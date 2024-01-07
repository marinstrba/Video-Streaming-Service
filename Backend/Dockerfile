FROM maven:3.9.6-eclipse-temurin-17-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the entire project (including parent POM)
COPY . .

# Change the working directory to the backend module
WORKDIR /app/Backend

# Build the backend module
RUN mvn clean install

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "target/Backend-0.0.1-SNAPSHOT.jar"]
