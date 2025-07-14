# EmployeeVault - Employee Management System

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.0-green.svg)
![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Swagger](https://img.shields.io/badge/Swagger-2.3.0-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

EmployeeVault is a Spring Boot REST API application for managing employee data with full CRUD operations, bulk processing, and advanced filtering capabilities. The API is now fully documented with Swagger UI for easy testing and integration.

## Features

- **Full CRUD Operations**: Create, Read, Update, and Delete employee records
- **Bulk Operations**: Create multiple employees in a single request
- **Advanced Search**: Filter employees by name, city, designation, or combinations
- **RESTful API**: Standard HTTP methods and status codes
- **Swagger UI**: Interactive API documentation
- **CORS Support**: Pre-configured for frontend development
- **JPA/Hibernate**: Database persistence with Spring Data JPA

## API Endpoints

| Method | Endpoint                     | Description                          |
|--------|------------------------------|--------------------------------------|
| GET    | `/api/employees`             | Get all employees or filter by name  |
| GET    | `/api/employees/{id}`        | Get a specific employee by ID       |
| POST   | `/api/employees`             | Create a new employee               |
| POST   | `/api/employees/bulk`        | Create multiple employees           |
| PUT    | `/api/employees/{id}`        | Update an existing employee         |
| DELETE | `/api/employees`             | Delete all employees                |
| DELETE | `/api/employees/{id}`        | Delete a specific employee          |
| GET    | `/api/employees/search`      | Search by city and/or designation   |

## Request/Response Examples

**Create Employee:**
```http
POST /api/employees
Content-Type: application/json

{
  "empName": "John Doe",
  "empDesignation": "Software Engineer",
  "empCity": "New York"
}
```

## Search Employees

```http
GET /api/employees/search?city=New York&designation=Manager
```

## Technologies Used

- **Backend**: Spring Boot 2.7.x
- **Database**: Any JPA-compatible database (H2, MySQL, PostgreSQL, etc.)
- **Build Tool**: Maven
- **Java Version**: 17

## Setup Instructions

### Prerequisites:
- Java 17 JDK
- Maven 3.8+
- Your preferred database

### Configuration:
- Update `application.properties` with your database credentials
- Configure CORS origins if needed

### Build and Run:
```bash
mvn clean install
mvn spring-boot:run
```

## API Access

The application runs on:  
`http://localhost:8080` (default port)

**Base API Path**:  
`/api/employees`

## Database Schema

```sql
CREATE TABLE Employee (
  EmpID BIGINT PRIMARY KEY AUTO_INCREMENT,
  EmpName VARCHAR(255) NOT NULL,
  EmpDesignation VARCHAR(255) NOT NULL,
  EmpCity VARCHAR(255) NOT NULL
);
```

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## Contributing

We welcome contributions! Here's how you can help:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a pull request

## Acknowledgments

Special thanks to:

- [Spring Boot Team](https://spring.io/projects/spring-boot)
- The Java Community
- All contributors who have helped improve this project
