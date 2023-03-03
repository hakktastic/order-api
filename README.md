# Demo Order API

This repository contains a demo application of a Order API which consists of four micro services:

1. product-service: REST service to read product(s)
2. cart-service: REST service to create, update and read cart(s)
3. order-service: REST service to create and read orders
4. report-service: to report about the orders

The main goal of this API is to demonstrate the orchestration of following:

- Spring Boot
- Java (functional programming & streams)
- Rest
- H2 & JPA
- Validations
- Error Handling
- Unit- & Integrations Tests (MockMVC, Assertions)
- OpenAPI
- Container Images
- Kubernetes
- Service Discovery with OpenFeign

## Prerequisites

To deploy the API a kubectl client with connection to a k8s cluster is needed. Optionally, for compiling the API and
building the container images Maven and
the Docker runtime is needed.

## Build

From the root directory, run the following shell script: [build.sh](build.sh)

## Deploy

From the root directory, run the following shell script: [deployment.sh](deployment.sh)

## Uninstall

For cleaning up container images and k8s workload resources, run the following
shell script: [undeploy.sh](undeploy.sh)

## API-Docs

- product-service: [http://[service-url]:30880/v3/api-docs]()
- cart-service:[http://[service-url]:30881/v3/api-docs]()
- order-service: [http://[service-url]:30882/v3/api-docs]()
- report-service: [http://[service-url]:30883/v3/api-docs]()

## Postman

The performing requests on the API's, one can use the Postman collections located in the [/postman](/postman) path.

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
- Usage of OpenFeign:
    - For demo purposes a declarative client to connect to other service.
      An alternative is to
      use [Spring Webclient](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/reactive/function/client/WebClient.html)
      for Reactive support, since OpenFeign does not support this
- Usage of Lombok with JPA:
    - For demo purposes Lombok is used with JPA. For production scenarios the usage of some Lombok
      annotations needs to be reconsidered
- Usage of Entity vs DTO:
    - For demo purposes Entities are used without begin mapped before exposing its data structure
      through the controllers. For production scenarios it might be wise to use DTO's for the controllers. 