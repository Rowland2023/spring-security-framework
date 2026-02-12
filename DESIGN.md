
# Technical Design & Architecture Notes

## 1. Modular Strategy: The "Core-Library" Pattern

The project follows a **Shared-Kernel** architecture. By abstracting security into a custom Spring Boot Starter, we achieve:

* **Enforced Standards:** All microservices using this starter automatically inherit the same security protocols (BCrypt, JWT validation, and RBAC).
* **Developer Velocity:** Application teams do not need to configure `HttpSecurity` or `JwtFilters`. They simply include the dependency.
* **Centralized Maintenance:** If a security vulnerability is found in the JWT logic, we patch it in the starter, and all services receive the fix upon their next build.

---

## 2. Security Implementation Details

### **Authentication Flow**

We utilize a `DaoAuthenticationProvider` backed by a custom `UserDetailsService`. This separates the **Authentication Mechanism** from the **Identity Store** (the Database), allowing for easy migration to LDAP or OAuth2 in the future without changing the core filter logic.

### **JWT Token Anatomy**

The tokens are signed using the `HS384` algorithm. The payload (Claims) includes:

* `sub`: The username.
* `roles`: A list of authorities (e.g., `ROLE_ADMIN`) used by the `AccessDecisionManager`.
* `iat/exp`: Standard timestamps to prevent replay attacks and ensure token expiration.

---

## 3. Cross-Cutting Concerns

### **Request Observability**

A custom `OncePerRequestFilter` was implemented in the starter to log the lifecycle of every request.

* **Goal:** Capture the "Who, What, Where, and Result."
* **Outcome:** Logs show the authenticated user, the HTTP method, the URI, and the final response status. This is critical for post-incident forensics.

### **Unified Exception Strategy**

The `GlobalExceptionHandler` uses `@RestControllerAdvice` to "trap" security-specific exceptions (`AccessDeniedException`, `AuthenticationException`) before they reach the default Spring Boot error page.

* **Format:** Standardized JSON containing `timestamp`, `status`, `error`, `message`, and `path`.
* **Benefit:** Provides a predictable contract for frontend consumers and prevents "information leakage" via stack traces.

---

## 4. Scalability & Trade-offs

### **Horizontal Scaling**

Because the security context is reconstructed from the JWT on every request, the application is **stateless**. This allows us to scale the `user-management-service` to  instances behind a load balancer without needing "sticky sessions."

### **Trade-off: Token Revocation**

For this assessment, we opted against server-side token blacklisting.

* **Trade-off:** If a token is compromised, it remains valid until expiry.
* **Mitigation:** In a high-security production environment, we would implement a short-lived Access Token / long-lived Refresh Token pattern with a Redis-backed revocation list.

---



