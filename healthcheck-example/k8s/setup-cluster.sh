#!/bin/bash

set -e  # Exit on error

echo "Setting up Kubernetes environment..."

# Check if Docker is running
if ! docker info >/dev/null 2>&1; then
    echo "Docker daemon is not running. Please start Docker first."
    exit 1
fi

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

# Setup local registry
echo "Setting up local registry..."
if docker ps | grep -q "registry:2"; then
    echo "Stopping existing registry..."
    docker stop registry && docker rm registry
fi

echo "Starting new registry..."
docker run -d --restart=always -p 5000:5000 --name registry registry:2

#Delete existing cluster if it exists
if kind get clusters | grep -q "healthcheck-cluster"; then
    echo "Deleting existing cluster..."
    kind delete cluster --name healthcheck-cluster
fi

# Create cluster with registry config if not exists

echo "Creating new cluster with registry configuration..."
cat <<EOF | kind create cluster --name healthcheck-cluster --config=-
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
containerdConfigPatches:
- |-
  [plugins."io.containerd.grpc.v1.cri".registry.mirrors."localhost:5000"]
    endpoint = ["http://registry:5000"]
nodes:
- role: control-plane
  extraPortMappings:
  - containerPort: 30085
    hostPort: 30085
    protocol: TCP
EOF
#fi
# Connect the registry to the cluster network
echo "Connecting registry to cluster network..."
docker network connect "kind" registry || true

# Build and push the application image
echo "Building application..."
ls 
cd ..
ls 
if [ ! -f ./mvnw ]; then
    echo "Maven wrapper not found. Please run this script from the project root directory."
    exit 1
fi
pwd 
./mvnw clean package -DskipTests
echo "Building Docker image..."
docker build -t localhost:5000/healthcheck-example:latest .
echo "Pushing image to local registry..."
docker push localhost:5000/healthcheck-example:latest

kind load docker-image healthcheck-example:latest --name healthcheck-cluster

# Deploy the application
echo "Deploying application to cluster..."
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/deployment.yaml

# Wait for deployment
echo "Waiting for deployment to be ready..."
kubectl wait --for=condition=available --timeout=300s deployment/healthcheck-app

# Verify health endpoint
echo "Verifying application health..."
echo "Waiting for application to start and checking health endpoint..."

MAX_RETRIES=5
RETRY_INTERVAL=10
retry_count=0

while [ $retry_count -lt $MAX_RETRIES ]; do
    echo "Attempt $((retry_count + 1)) of $MAX_RETRIES..."
    
    # Print pod status for debugging
    echo "Pod status:"
    kubectl get pods -l app=healthcheck-app
    
    # Check pod logs
    echo "Pod logs:"
    kubectl logs -l app=healthcheck-app --tail=20

    response=$(curl -f -s http://localhost:30085/actuator/health || echo "failed")
    if [ "$response" != "failed" ] && [ ! -z "$response" ]; then
        echo "Health check successful! Response:"
        echo "$response"
        break
    else
        echo "Health check failed or empty response. Retrying in $RETRY_INTERVAL seconds..."
        ((retry_count++))
        if [ $retry_count -lt $MAX_RETRIES ]; then
            sleep $RETRY_INTERVAL
        fi
    fi
done

if [ $retry_count -eq $MAX_RETRIES ]; then
    echo "Health check failed after $MAX_RETRIES attempts"
    echo "Final pod status:"
    kubectl describe pod -l app=healthcheck-app
    exit 1
fi

echo "Setup completed successfully!"
echo "You can access the application at http://localhost:30085"
echo "Health check endpoint: http://localhost:30085/actuator/health"
