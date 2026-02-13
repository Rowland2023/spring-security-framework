

🔐 Spring Boot Security Framework

This project demonstrates a production‑grade, multi‑module security architecture. It features a reusable **Security Spring Boot Starter** (library) and a **User Management Service** (sample application) to showcase clean architecture, modularization, and security best practices.

## 🏗️ 1. Architecture Overview

Following Clean Architecture principles, the solution is split into two distinct modules:

### **security-spring-boot-starter**

A reusable library encapsulating cross‑cutting concerns:

* **JWT handling & validation:** Claims-based tokens (userId, roles, expiry).
* **Filter chains:** Custom security filters for token extraction.
* **Role‑based access control (RBAC):** Method and URL-level security.
* **Global exception handling:** Unified error responses.
* * **Request logging:** Principal-aware audit logs.


It uses Spring Boot auto‑configuration via `AutoConfiguration.imports` to remain plug‑and‑play for any consuming service.

### **user-management-service**

The business application consuming the starter. It manages the User entity and implements domain‑specific logic while delegating all security concerns to the starter.

---

## 🚀 2. Getting Started

### **Prerequisites**

* Java 21
* Maven 3.9+
* PostgreSQL (running locally with a database named `security_db`)

### **Database Setup**

```sql
CREATE DATABASE security_db;
CREATE USER security_user WITH ENCRYPTED PASSWORD 'securepassword';
GRANT ALL PRIVILEGES ON DATABASE security_db TO security_user;

```

### **Build & Install**

Since the service depends on the internal library, you must install the library to your local Maven repository first:

```bash
# From the root directory
cd security-spring-boot-starter
mvn clean install

# Navigate to the service and run
cd ../user-management-service
mvn clean package
java -jar target/user-management-service-1.0.0-SNAPSHOT.jar

```

---

## ⚙️ 3. Configuration

Key properties (override via `application.yml` or environment variables):

```yaml
security:
  jwt:
    secret: ${JWT_SECRET:my-super-secret-key-at-least-256-bits-long}
    expiry: 3600000   # 1 hour in ms

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/security_db
    username: security_user
    password: securepassword

```

---

## 🛠️ 4. API Verification Guide

### **Authentication**

**POST** `/api/v1/auth/login`

*Returns a JWT containing: userId, username, roles, and expiry.*

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
-H "Content-Type: application/json" \
-d '{"username": "admin", "password": "password"}'

```

### **Authorization & RBAC**

| Endpoint | Access Level | Example Command |
| --- | --- | --- |
| `/api/v1/public/health` | Public | `curl -i http://localhost:8080/api/v1/public/health` |
| `/api/v1/user/me` | Authenticated | `curl -H "Authorization: Bearer <TOKEN>" http://localhost:8080/api/v1/user/me` |
| `/api/v1/admin/users` | Admin Only | `curl -H "Authorization: Bearer <TOKEN>" http://localhost:8080/api/v1/admin/users` |

---

## 🛡️ 5. Cross‑Cutting Concerns

* **JWT Security:** Stateless `OncePerRequestFilter` implemented in the starter.
* **Logging:** `RequestLoggingFilter` logs authenticated user, method, URI, and response status.
* **Unified Error Handling:** Centralized `@RestControllerAdvice` ensures consistent JSON error responses for 401, 403, and 500.

---

## 🧠 6. Design Decisions & Trade‑offs

* **Starter Pattern:** Uses `AutoConfiguration.imports` for modern Spring Boot 3+ convention‑over‑configuration.
* **Statelessness:** `SessionCreationPolicy.STATELESS` ensures scalability in cloud/container environments.
* **JWT Revocation Trade‑off:** Tokens are stateless for simplicity in this assessment. In a production environment, a Redis‑based denylist would be implemented for immediate revocation.

---

## 🧪 7. Testing Strategy

Integration Tests are implemented with **MockMvc** in the `user-management-service`.

* **Coverage:** Public endpoint access, unauthorized 401/403 scenarios, and successful role-based access.

```bash
# Run tests from root
mvn test

```

---

## 📌 8. Known Warnings

* **AuthenticationProvider Warning:** A custom `AuthenticationProvider` is configured to integrate with JPA users. This is intentional to demonstrate a custom security provider implementation.
* **Open‑in‑View Warning:** Disabled via `spring.jpa.open-in-view=false` to adhere to performance best practices.

---

