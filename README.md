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

## Design considerations
- Usage of H2:
  - for demo purposes a lightweight in-memory database is used
  - for production purposes more heavy weight databases would be advised 
  (Postgres, MariaDB, MongoDB, etc.)
- Usage of shell scripts:
  - for demo purposes a shell script is used for installing containers & k8s resources
  - for production purposes usage of Helm or Kustomize would be advised i.c.m.
  with an automated CI/CD for installing k8s resources
  - for production purposes usage of CI/CD features for building container images 
  would be advised