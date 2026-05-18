# TaskFlow Mini API 🚀

A professional lightweight REST API for Task Management built with Spring Boot, JPA, and PostgreSQL.

## Features
- **User Management**: Sign up and Sign in functionality.
- **Task Management**: Create, Read, and List tasks per user.
- **Auto-generated IDs**: Uses UUIDs for better security and scalability.
- **API Documentation**: Integrated with Swagger (OpenAPI).

## Tech Stack
- Java 17+
- Spring Boot 4.x
- Spring Data JPA
- PostgreSQL
- Lombok

## How to Run
1. Clone the repository.
2. Configure your PostgreSQL database in `src/main/resources/application.yaml`.
3. Run the app using `./mvnw spring-boot:run`.

## API Documentation
Once the app is running, visit:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
