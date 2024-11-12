#!/bin/bash

# Script to recompile projects, rebuild Docker images, and run the updated version


# Print commands before executing them
# set -x

echo "Starting recompilation and deployment process..."

# Stop any running Docker containers
#echo "Stopping existing Docker containers..."
#docker-compose down

# Exit on any error
 set -e
# Recompile each project using Maven

#echo "Run docker Zipkin..."
# docker run -d -p 9411:9411 openzipkin/zipkin
echo "Running projects with Maven..."
export cmd
for project in */pom.xml; do
    project_dir=$(dirname "$project")
    echo "Running $project_dir"
     (cd "$project_dir" && mvn spring-boot:run -DskipTests &  )
done

ps aux | grep java

