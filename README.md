# Demo Order API
This repository contains a demo application of a Order API.

## Prerequisites
To deploy the API a kubectl client with connection to a k8s cluster is needed.

Optionally, for compiling the API and building the container images Maven and 
the Docker runtime is needed.

## Build
From the root directory, run the following shell script: [build.sh](build.sh)

## Deploy
From the root directory, run the following shell script: [deployment.sh](deployment.sh)

## Uninstall
For cleaning up container images and k8s workload resources, run the following
shell script: [undeploy.sh](undeploy.sh)