# Use a base image that includes Java
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

COPY target/MarketPg-0.0.1-SNAPSHOT.jar /app/MarketPg-0.0.1-SNAPSHOT.jar

# Expose the port your Spring Boot app will run on (default is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "MarketPg-0.0.1-SNAPSHOT.jar"]