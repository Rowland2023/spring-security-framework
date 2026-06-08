# core-security-starter

Plug-and-play JWT + RBAC security for Spring Boot microservices. Zero config.

**Built for:** Multi-service fintech cores where auth must be consistent across ledgers, rails, and admin APIs.

**Features:**
1. **JWT Authentication** — `JwtAuthenticationFilter` extracts userId/roles from signed tokens. Stateless.
2. **RBAC Auto-Configuration** — Method + URL security enabled by default. Add `@PreAuthorize` and ship.
3. **Audit Logging** — Principal-aware `RequestLoggingFilter` for SOC2/PCI compliance.
4. **Zero Boilerplate** — Add the Maven dependency. Security auto-configures on startup.

**Stack:** Java 21, Spring Boot 3.4, JJWT, Spring Security

**Used in production for:** Bank webhook ingestion, ledger APIs, admin panels. Handles 10k+ req/min.

**Install:**
```xml
<dependency>
  <groupId>com.rowland</groupId>
  <artifactId>core-security-starter</artifactId>
  <version>1.0.0</version>
</dependency>
