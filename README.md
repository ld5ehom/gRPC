# gRPC Server Practice

This project is a hands-on practice for building a gRPC server in a Spring-based MSA environment.
It focuses on implementing gRPC server APIs and server-side interceptors using an existing online bookstore domain previously built with REST APIs.
All gRPC APIs are tested using Insomnia.

## Development Environment

- OS: macOS Sonoma 
- IDE: IntelliJ IDEA 
- JDK: OpenJDK 17
- Build Tool: Gradle
- Version Control: Git

---

## Tech Stack

### Spring and Persistence

- org.springframework.boot:spring-boot-starter-data-jpa
- org.springframework.boot:spring-boot-starter-actuator
- org.projectlombok:lombok
- com.h2database:h2

### gRPC Server

- net.devh:grpc-server-spring-boot-starter
- io.grpc:grpc-netty-shaded
- io.grpc:grpc-protobuf
- io.grpc:grpc-stub
- javax.annotation:javax.annotation-api

## Protobuf and gRPC Configuration

This project uses Protocol Buffers to define gRPC services.
Proper protoc configuration is required depending on the operating system.

- protobuf version: 3.25.1
- gRPC version: 1.60.1
- protoc platform (macOS): osx-x86_64

### Apple Silicon (M1/M2) Note

On Apple Silicon environments, Rosetta is required to run x86_64 binaries.

- Command: softwareupdate --install-rosetta --agree-to-license

## Practice Flow

1. Set up a Spring Boot project with gRPC server support
2. Implement basic gRPC APIs
3. Reuse the online bookstore domain from previous REST API practice
4. Test gRPC APIs using Insomnia

## Practice Details

### Spring-based Environment Setup

- Create a Spring Boot project with gRPC server dependencies
- Verify basic gRPC server startup

### gRPC Server API Implementation

- Design protobuf definitions
- Implement gRPC services using Spring Boot
- Connect existing Entity and Repository layers

### gRPC Server Interceptor Implementation

- Implement server-side interceptors for authentication and access logging
- Apply interceptors to gRPC services

### gRPC API Testing

- Test implemented gRPC APIs using Insomnia
- Verify request and response behavior

## Scope

- gRPC server-side implementation
- Unary and server-streaming APIs
- ServerInterceptor for authentication and access logging
- Domain reuse from an existing REST-based bookstore application
