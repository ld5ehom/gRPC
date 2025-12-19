# gRPC Client Practice

This project is a hands-on practice for implementing a gRPC client using Spring Boot.
It focuses on consuming existing gRPC server APIs and exposing the results through REST endpoints.
This practice is part of a broader backend API study covering REST, gRPC, and GraphQL in an MSA environment.

## Development Environment

- IDE: IntelliJ IDEA 
- JDK: OpenJDK 17
- Build Tool: Gradle

## Tech Stack

### Spring

- spring-boot-starter
- spring-boot-starter-web
- spring-boot-starter-actuator
- lombok

### gRPC Client

- grpc-client-spring-boot-starter (net.devh)
- grpc-netty-shaded
- grpc-protobuf
- grpc-stub

## Protobuf and gRPC Configuration

This project uses Protocol Buffers to generate gRPC client stubs.
Proper protoc configuration is required depending on the operating system.

- protobuf version: 3.25.1
- gRPC version: 1.60.1
- protoc platform (macOS): osx-x86_64

## Practice Flow

1. Set up a Spring Boot project with gRPC client support
2. Configure gRPC client stubs
3. Connect to an existing gRPC server
4. Call gRPC APIs from the client
5. Expose gRPC responses through Spring REST controllers

## Practice Details

### Environment Setup

- Create a Spring Boot project with gRPC client dependencies
- Verify gRPC client configuration and startup

### gRPC Client Implementation

- Connect to an existing gRPC server
- Invoke gRPC APIs using generated stubs
- Convert gRPC responses to REST responses

## Scope

- gRPC client-side implementation
- Blocking gRPC stub usage
- REST-to-gRPC integration
- Client-side configuration and testing

## Notes

This project focuses on understanding gRPC client behavior and integration patterns.
Advanced topics such as retries, load balancing, and client interceptors are out of scope for this practice.
