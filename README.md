Markdown
# Spring Boot Modular Security Framework

A production-ready Authentication and Authorization system built with Spring Boot 3.4.2. This project demonstrates a modular "Clean Architecture" approach by decoupling security infrastructure into a reusable Spring Boot Starter.

## 1. Project Structure
The solution is divided into two primary Maven modules:
* **`core-security-starter`**: A reusable library providing auto-configuration for JWT authentication, BCrypt hashing, and global security filters.
* **`user-management-service`**: A sample microservice that consumes the starter to protect endpoints and manage user data.

## 2. Key Design Decisions
* **Custom Spring Boot Starter**: Security logic is abstracted into the `autoconfigure` package. By using `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`, the library is plug-and-play.
* **Stateless Authentication**: Uses JWT to eliminate the need for server-side sessions, ensuring scalability.
* **Method-Level Security**: Enabled `@EnableMethodSecurity` to allow fine-grained access control using `@PreAuthorize` on controller methods.
* **Global Exception Handling**: Centralized handling for 401 (Unauthorized) and 403 (Forbidden) ensures a consistent API error format.

## 3. Tech Stack
* **Java 21**
* **Spring Boot 3.4.2**
* **Spring Security 6**
* **JSON Web Token (jjwt)**
* **PostgreSQL** (Database)
* **Lombok** (Boilerplate reduction)

---

## 4. Getting Started

### Prerequisites
* JDK 21
* Maven 3.8+
* PostgreSQL running on `localhost:5432`

### Build
From the root directory, run:
```bash
mvn clean install -DskipTests
Run
Launch the application by providing database credentials via CLI:

Bash
java -jar user-management-service/target/user-management-service-1.0.0-SNAPSHOT.jar \
--spring.datasource.url=jdbc:postgresql://localhost:5432/security_db \
--spring.datasource.username=postgres \
--spring.datasource.password=yourpassword
5. API Usage & Examples
I. Public Health Check
Endpoint: GET /api/v1/public/health

Bash
curl -i http://localhost:8080/api/v1/public/health
II. Authentication (Login)
Endpoint: POST /api/v1/auth/authenticate
Note: A default admin user (admin/admin123) is created on startup if the DB is empty.

Bash
curl -X POST http://localhost:8080/api/v1/auth/authenticate \
-H "Content-Type: application/json" \
-d '{"username": "admin", "password": "admin123"}'
Response returns a token.

III. Protected "Me" Endpoint
Endpoint: GET /api/v1/user/me

Bash
curl -i http://localhost:8080/api/v1/user/me \
-H "Authorization: Bearer <YOUR_JWT_TOKEN>"
IV. Admin Only Endpoint
Endpoint: GET /api/v1/admin/users

Bash
curl -i http://localhost:8080/api/v1/admin/users \
-H "Authorization: Bearer <YOUR_ADMIN_TOKEN>"