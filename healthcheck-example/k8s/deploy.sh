#!/bin/bash

# Check if Kind is installed
if ! command -v kind &> /dev/null; then
    echo "Kind is not installed. Installing Kind..."
    curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.20.0/kind-linux-amd64
    chmod +x ./kind
    sudo mv ./kind /usr/local/bin/
fi

# Check if kubectl is installed
if ! command -v kubectl &> /dev/null; then
    echo "kubectl is not installed. Installing kubectl..."
    curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
    chmod +x kubectl
    sudo mv kubectl /usr/local/bin/
fi

# Create Kind cluster if it doesn't exist
if ! kind get clusters | grep -q "healthcheck-cluster"; then
    echo "Creating Kind cluster..."
    kind create cluster --name healthcheck-cluster
else
    echo "Kind cluster already exists"
fi

# Build Docker image
echo "Building Docker image..."
docker build -t healthcheck-example:latest ..

# Load image into Kind
echo "Loading image into Kind cluster..."
kind load docker-image healthcheck-example:latest --name healthcheck-cluster

echo "Applying Kubernetes configurations..."

# Apply ConfigMap
kubectl apply -f configmap.yaml

# Apply Deployment and Service
kubectl apply -f deployment.yaml

echo "Waiting for deployment to be ready..."
kubectl wait --for=condition=available --timeout=60s deployment/healthcheck-app

# Get the port mapping
NODE_PORT=$(kubectl get svc healthcheck-service -o=jsonpath='{.spec.ports[0].nodePort}')
echo "Application deployed successfully!"
echo "You can access the application at http://localhost:$NODE_PORT"
echo "Health check endpoint: http://localhost:$NODE_PORT/actuator/health"

# Add cleanup instructions
echo ""
echo "To clean up the environment:"
echo "1. Run: kind delete cluster --name healthcheck-cluster"
