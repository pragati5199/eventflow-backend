# EventFlow Backend

EventFlow is a backend-first system designed to handle high-concurrency event bookings with strict capacity management.

This project is built as a learning-focused but production-grade backend system, emphasizing:
- clean architecture
- transactional correctness
- concurrency handling
- scalability tradeoffs

## Tech Stack
- Java 17
- Spring Boot
- Maven
- PostgreSQL
- JPA / Hibernate

## Project Structure
src/main/java/com/eventflow
├── controller # REST APIs
├── service # Business logic
├── repository # Database access
├── domain # Core domain entities
├── dto # Request/response models
├── exception # Custom exceptions
├── config # Configuration classes


## Current Status
- Project bootstrap completed
- Base package structure in place
- Domain modeling in progress

## How to Run
```bash
./mvnw spring-boot:run
