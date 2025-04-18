#!/bin/bash

# export DOCKER_USERNAME="your_docker_username" && export DOCKER_PASSWORD="your_docker_password"
# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Maven is not installed. Installing Maven..."
    sudo apt update
    sudo apt install maven -y
fi

# Check for Docker Hub credentials
if [ -z "$DOCKER_USERNAME" ] || [ -z "$DOCKER_PASSWORD" ]; then
    echo "Please set DOCKER_USERNAME and DOCKER_PASSWORD environment variables"
    exit 1
fi

# Build Maven project
echo "Building Maven project..."
cd .. && mvn clean package

# Login to Docker Hub
echo "Logging in to Docker Hub..."
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

# Build and tag Docker image
echo "Building Docker image..."
docker build -t healthcheck-example:latest .
docker tag healthcheck-example:latest "$DOCKER_USERNAME/healthcheck-example:latest"

# Push to Docker Hub
echo "Pushing image to Docker Hub..."
docker push "$DOCKER_USERNAME/healthcheck-example:latest"

echo "Build and push completed successfully!"
