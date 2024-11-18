# Task Management Backend Service
Welcome to the Task Management Backend Service! This project offers a robust solution for managing tasks via a secure RESTful API. Users can perform CRUD operations with high-quality code, error handling, validation, and logging. Built with Spring Boot and OOP principles, it ensures scalability and maintainability. Enjoy seamless user experience and top-notch security with built-in unit testing and API security.

## Features
- **Task CRUD Operations**: Create, read, update, and delete tasks.
- **Spring Boot**: Rapid development with Spring Boot, ensuring high performance and scalability.
- **In-memory Efficient Database**: In-memory database for fast data access and efficient management.
- **REST APIs**: All functionalities exposed via RESTful API endpoints.
- **Error Handling**: Global error handler to catch and respond to exceptions.
- **Validation Logic**: Ensures data integrity and business logic validation for inputs.
- **Logging**: Detailed logging for tracking system behavior and troubleshooting.
- **Spring Security (API Security)**: Secure APIs with JWT-based authentication for authorized access.
- **Unit Testing**: Comprehensive unit tests ensuring code quality and reliability.
- **OOP Design Principles**: Clean code with encapsulation, inheritance, and polymorphism.
- **MVC Pattern**: A clean separation of concerns with Model-View-Controller architecture.
- **HTTP Status Codes**: Meaningful HTTP status codes to communicate the result of requests.

## Tech Stack
- **Java**: Core programming language for backend development.
- **Spring Boot**: Framework for building enterprise-grade applications.
- **Gradle**: Build tool for dependency management and project builds.
- **Maven Repository**: Repository for managing dependencies.
- **Postman**: API testing tool to interact with the endpoints and test requests.

## Prerequisites

Before running the application, ensure that the following are installed:

1. **Java 21** - [Download Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. **Gradle 8** - [Download Gradle](https://gradle.org/install/).

3. **Postman** - [Download Postman](https://www.postman.com/downloads/).

## Running the Spring Boot Application

Once Java, Gradle, and Postman are installed, open your terminal follow these steps:

1. Clone the repository:

```bash
git clone https://github.com/yuvraj0028/wellness360-assignment.git
```

2. Change your directory:

```bash
cd wellness360-assignment
```

3. Build the application:

```bash
gradlew build
```

5. Run tests (optional but recommended):
```bash
gradlew test
```

4. Run the application:

```bash
gradlew bootRun
```

## Application Port
The application runs on port 9094. To access the APIs, use the base URL:
http://localhost:9094/api/v1.

## Steps to Get Authorization
1. Use the endpoint `http://localhost:9094/api/v1/auth/signup` to sign up and get an authentication token.
2. If you are already logged in but do not have the auth token, use the endpoint `http://localhost:9094/api/v1/auth/login`.

## Request Body for Signup and Login:

#### Request Body for Signup and Login:

```json
{
    "email": "user@test.com",
    "password": "User@123"
}
```

### IMPORTANT:
Once you have the token, it is mandatory to set the Bearer token in Postman for every API call other than the signup and login endpoints.

#### Postman Steps:
![image](https://github.com/user-attachments/assets/ad9e47a6-0ad5-4536-a1fd-9bed7cfdbc71)
- Open Postman
- Go to the Authorization tab
- Select **Bearer** Token
- Paste the token in the provided field.

## API Endpoints
1. **Get all tasks**:
**GET** `http://localhost:9094/api/v1/tasks`
Fetch all tasks in the system.

2. **Get Task by ID**:
**GET** `http://localhost:9094/api/v1/tasks/{id}`
Fetch a task by its **ID** (ID is required).

3. **Create Task**:
**POST** `http://localhost:9094/api/v1/tasks`
Create a new task.
Request Body:
#### Request Body:
```json
{
    "title": "task",
    "description": "description",
    "dueDate": "28-11-2024"
}
```
The **dueDate** must be in the format `dd-MM-yyyy`. The default status of the task is `PENDING`.

4. **Update Task by ID**:
**PUT** `http://localhost:9094/api/v1/tasks/{id}`
Update a task by its **ID**.
Request Body:
```json
{
    "title": "task",
    "description": "description",
    "status": "IN_PROGRESS",
    "dueDate": "28-11-2024"
}
```
**Status** should be one of the following: `PENDING`, `IN_PROGRESS`, or `COMPLETED`.

5. **Delete Task by ID**:
**DELETE** `http://localhost:9094/api/v1/tasks/{id}`
Delete a task by its **ID**.

6. **Mark Task as Completed**:
**PUT** `http://localhost:9094/api/v1/tasks/{id}/complete`
Mark a task as completed. **ID** is required.

## Exception Handling
The application includes a global exception handler to manage errors gracefully. It provides meaningful responses with HTTP status codes, ensuring users can easily understand issues when interacting with the API.

## Validation Logic
The service ensures that all inputs, such as task details and dates, are validated before processing. This prevents incorrect or incomplete data from being stored and ensures business rules are followed.

## Error Handling & Logging
Comprehensive error handling ensures that all errors are caught and logged, providing detailed information for debugging and operational insights. The logging follows best practices to capture key events, errors, and other important information.

***

Thank you for exploring the Task Management Backend Service. Whether you're building on this service or using it in your own projects, it’s designed to be simple, secure, and scalable.
