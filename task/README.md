# Task Management API

A simple RESTful Task Management API built using Spring Boot.

---

## Tech Stack

- Java 17
- Spring Boot 3
- Maven
- In-memory storage using ConcurrentHashMap

---

## Features

- Create Task
- List All Tasks
- Update Task Status
- Delete Task
- Proper HTTP status codes
- Global Exception Handling
- Request Validation
- Layered Architecture (Controller → Service → Repository)

---

## Architecture Design

Controller → Handles HTTP requests  
Service → Business logic  
Repository → In-memory storage  
Exception → Global error handling  

Storage uses:
- ConcurrentHashMap (thread-safe)
- AtomicLong (safe ID generation)

---

## API Endpoints

### 1. Create Task

POST `/tasks`

Request Body:

{
  "title": "Interview Task",
  "description": "Test task"
}

Response:
- 201 Created
- Returns created task

---

### 2. Get All Tasks

GET `/tasks`

Response:
- 200 OK
- Returns list of tasks

---

### 3. Update Task Status

PUT `/tasks/{id}?status=COMPLETED`

Response:
- 200 OK
- Returns updated task

Available Status values:
- PENDING
- IN_PROGRESS
- COMPLETED

---

### 4. Delete Task

DELETE `/tasks/{id}`

Response:
- 204 No Content

---

## Error Handling

### 404 - Task Not Found

{
  "timestamp": "2026-02-20T10:00:00",
  "error": "NOT_FOUND",
  "message": "Task not found with id 99"
}

### 400 - Validation Error

{
  "timestamp": "2026-02-20T10:00:00",
  "error": "VALIDATION_ERROR",
  "message": "Title is required"
}

### 500 - Internal Server Error

{
  "timestamp": "2026-02-20T10:00:00",
  "error": "INTERNAL_SERVER_ERROR",
  "message": "Unexpected error occurred"
}

---

## Running the Application

Run:

./mvnw spring-boot:run

Application runs on:

http://localhost:8080

Use Postman to test endpoints.

---

## Recently added Improvements

- Error Code Standardization – Define consistent error codes for predictable client-side error handling (Implemented this)
- Add logging & Monitoring - Structured logging and Spring Actuator metrics for health checks. (Implemented using AOP & Actuators)
- Add unit tests - Add JUnit and Mockito unit tests along with @SpringBootTest integration tests for API validation.
- Add DTO layer - To decouple API contracts from domain models and avoid over-exposing internal fields.

---

## Future Improvements

- Persistence layer - Replace in-memory storage with database (Spring Data JPA)
- API Versioning – Introduce versioned endpoints (e.g., /api/v1/tasks) to maintain backward compatibility.
- Add pagination - Add pagination and sorting to efficiently handle large task datasets.
  - Not required for small datasets
- Authentication & Authorization – Secure APIs using Spring Security with JWT or OAuth2-based role access control. 
  - Will change flow to Client → Security Filter → Authentication Manager → Controller → Service. 
  - Not implementing now as testing becomes hard every API requires authorization bearer token.


---

## Commands helpful to run in mac

- ./mvnw clean install - clean and builds the application
- ./mvnw spring-boot:run - runs the spring boot application
-  CREATE TASK 
curl -X POST http://localhost:8080/tasks \
-H "Content-Type: application/json" \
-d '{"title":"Interview Task","description":"Testing validation"}'

- VALIDATION TEST
curl -X POST http://localhost:8080/tasks \
-H "Content-Type: application/json" \
-d '{"title":"","description":"Invalid"}'

- GET ALL
curl http://localhost:8080/tasks

- UPDATE STATUS
curl -X PUT "http://localhost:8080/tasks/1?status=COMPLETED"

- UPDATE INVALID ID
curl -X PUT "http://localhost:8080/tasks/99?status=COMPLETED"

- DELETE
curl -X DELETE http://localhost:8080/tasks/1




