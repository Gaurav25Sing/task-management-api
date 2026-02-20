# Task Management API

Simple RESTful Task Management API built with Spring Boot.

## Tech Stack

- Java 17
- Spring Boot 3
- Maven
- In-memory storage (ConcurrentHashMap)

---

## Features

- Create Task
- List All Tasks
- Update Task Status
- Delete Task
- Global Exception Handling
- Validation Support
- Layered Architecture (Controller → Service → Repository)

---

## Architecture

Controller → Handles HTTP requests  
Service → Business logic  
Repository → In-memory storage  
Exception → Global error handling  

Storage is implemented using `ConcurrentHashMap` and `AtomicLong` for thread-safe ID generation.

---

## API Endpoints

### 1 Create Task
POST `/tasks`

```json
{
  "title": "Interview Task",
  "description": "Test task"
}