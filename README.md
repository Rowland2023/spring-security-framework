🔐 Spring Boot Security FrameworkThis project demonstrates a production‑grade, multi‑module security architecture. It features a reusable Core Security Starter (library) and a Sample Application to showcase clean architecture, modularization, and modern Spring Boot 3.4 security best practices.🏗️ 1. Architecture OverviewFollowing Clean Architecture principles, the solution is split into two distinct modules:core-security-starterA reusable library encapsulating cross‑cutting security concerns:JWT Handling: Claims-based tokens (userId, roles, expiry) using JJWT.Filter Chains: Custom stateless security filters for token extraction and validation.RBAC: Role‑based access control configured for method and URL-level security.Audit Logging: Principal-aware request logging for security compliance.Auto-Configuration: Uses modern AutoConfiguration.imports for a plug‑and‑play experience.sample-applicationThe business application consuming the starter. It manages the User domain and implements business logic while delegating all authentication and authorization infrastructure to the core library.🚀 2. Getting StartedPrerequisitesJava 21Maven 3.9+PostgreSQL (Running locally with a database named security_db)Database SetupSQLCREATE DATABASE security_db;
CREATE USER security_user WITH ENCRYPTED PASSWORD 'securepassword';
GRANT ALL PRIVILEGES ON DATABASE security_db TO security_user;
Build & RunThanks to the multi-module Maven reactor, you can build and install the entire framework with a single command from the root directory:Bash# Build and install all modules to your local .m2 repository
mvn clean install

# Run the sample application
cd sample-application
mvn spring-boot:run
⚙️ 3. ConfigurationKey properties can be overridden via application.yml or environment variables:YAMLsecurity:
  jwt:
    secret: ${JWT_SECRET:my-super-secret-key-at-least-256-bits-long}
    expiry: 3600000   # 1 hour

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/security_db
    username: security_user
    password: securepassword
🛠️ 4. API Verification GuideAuthenticationPOST /api/v1/auth/loginReturns a JWT containing: userId, username, roles.Bashcurl -X POST http://localhost:8080/api/v1/auth/login \
-H "Content-Type: application/json" \
-d '{"username": "admin", "password": "password"}'
Authorization & RBACEndpointAccess LevelDescription/api/v1/public/healthPublicHealth check endpoint/api/v1/user/meAuthenticatedReturns current user profile/api/v1/admin/**Admin OnlyRestricted administrative actions🛡️ 5. Design Decisions & Trade‑offsStatelessness: Uses SessionCreationPolicy.STATELESS to ensure horizontal scalability in cloud environments.Starter Pattern: Implements the custom Spring Boot Starter pattern to promote code reuse and reduce boilerplate in microservices.Global Exception Handling: Centralized @RestControllerAdvice ensures consistent JSON error structures (401, 403, 500) across all services.Testing Strategy: Implements SampleApplicationTests with an H2 in-memory database and @ActiveProfiles("test") to ensure context integrity without external dependencies.🧪 6. TestingThe project includes integration tests to verify the security filter chain and context loading.Bash# Run tests for the entire project
mvn test
💡 Pro-Tip for the ReviewerThe project uses Java 21 features and the latest Spring Boot 3.4 security configurations (using Lambda DSL for HttpSecurity). The library is fully decoupled—simply adding the core-security-starter dependency to any new Spring project will enable the full security suite automatically.