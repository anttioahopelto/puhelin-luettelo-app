# Base image for sbt to build scala project
FROM sbtscala/scala-sbt:eclipse-temurin-11.0.14.1_1.6.2_2.12.15 as build

# Set the working directory
WORKDIR /app

# Copy sbt configuration and project files
COPY build.sbt /app/
COPY project /app/project

# Copy the source code
COPY src /app/src

# Install dependencies and build the backend
RUN sbt compile
RUN sbt assembly

# Use amazoncorretto as the base image for running the application
FROM amazoncorretto:8-alpine-jre

# Set the working directory for the final stage
WORKDIR /app

# Copy the compiled backend fat jar file from the build stage
COPY --from=build /app/target/scala-2.11/puhelin-luettelo-app-assembly-0.1.jar /app/puhelin-luettelo-app.jar

# Copy the frontend code
COPY UI /app/UI

# Expose the port your application will run on
EXPOSE 8080

# Command to run the backend server
CMD ["java", "-jar", "/app/puhelin-luettelo-app.jar"]
