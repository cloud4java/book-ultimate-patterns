#!/bin/bash

echo "Setting up Kubernetes environment..."

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

echo "Cluster setup completed!"
echo "To deploy the application, run: ./deploy-app.sh"
