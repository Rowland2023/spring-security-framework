# Spring Security JWT Project

This project follows Clean Architecture principles, separating Security logic from Business logic.

## Modules
- `auth-service`: Handles JWT generation and validation.
- `api-gateway` (or other module): Handles request routing and security filtering.

## Setup Requirements
* **JDK:** 17 or higher
* **Maven:** 3.8+ 

## Build Instructions
From the root directory, run:
```bash
./mvnw clean install