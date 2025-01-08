# Cargo Management System

This project is a **Cargo Management System** developed using a microservices architecture. The application manages cargo operations and tracking by integrating with various microservices.

## Features

- **Cargo Management**: Users can initiate cargo shipments, track their status, and receive updates on the cargo's journey.
- **Microservice Integration**: The system communicates with external services through REST APIs.
- **Swagger API Documentation**: APIs are documented and can be tested using Swagger.
- **DTO (Data Transfer Object)**: DTO has been used in scenarios where minimizing method calls or serializing data more efficiently is required.
- **Database**: PostgreSQL is used to store cargo and tracking data.
- 
### Technologies Used

- **Spring Boot**: The core framework for building the application.
- **JPA and PostgreSQL**: Used for database interactions and storage.
- **WebClient**: Facilitates communication with external services.
- **Docker**: Used for containerization of the application, PostgreSQL, and Redis services.
- **Swagger**: Provides API documentation and testing functionality.

### Dependencies

- **PostgreSQL**: Required for database connectivity.
- **Redis**: Used for cache and session management.


### Testler

In this project **JUnit 5**, **Mockito** ve **Spring Boot Test** are used for software testing.

### Test Scenarios

The tests in the project cover the following main scenarios:

1. **Service Tests**:
2. **Database Tests**
3. **Integration Tests**
4. **External Service Connections**





![image](https://github.com/user-attachments/assets/b264f56d-784b-4a91-aa2c-9291767c24b2)
![image](https://github.com/user-attachments/assets/cf7b3eff-5294-43d2-ad7a-011b8bbb09da)
![image](https://github.com/user-attachments/assets/fb9ecc37-cb69-45f1-993e-c36afedfd497)


