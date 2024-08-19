#!/bin/bash

# Script to recompile projects, rebuild Docker images, and run the updated version

# Exit on any error
set -e

# Print commands before executing them
set -x

echo "Starting recompilation and deployment process..."

# Stop any running Docker containers
echo "Stopping existing Docker containers..."
docker-compose down

# Recompile each project using Maven
echo "Recompiling projects with Maven..."
for project in */pom.xml; do
    project_dir=$(dirname "$project")
    echo "Compiling project in $project_dir"
    (cd "$project_dir" && mvn clean install -DskipTests)
done

# Rebuild Docker images
echo "Rebuilding Docker images..."
docker-compose build

# Start the containers using docker-compose
echo "Starting Docker containers..."
docker-compose up -d

echo "Recompilation and deployment process completed successfully."

# Check the status of the containers
docker-compose ps
