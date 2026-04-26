# Common Library (`com.kva:common-library`)

Shared Java library consumed by **every backend service** in the Services project. Packaged as a plain JAR (no runtime, no exposed ports, no database) and installed to the local Maven repo. Centralizes DTOs, Kafka topic names, JWT helpers, enums, and common exceptions so microservices don't drift out of sync.

---

## At a glance
| | |
|---|---|
| **Artifact** | `com.kva:common-library:1.0.0` |
| **Packaging** | `jar` |
| **Type** | Library (no `main` method) |
| **Java** | 21 (Temurin) |
| **Spring Boot** | 3.3.5 (parent BOM only — not started as an app) |
| **Runs as a process?** | No |
| **Consumed by** | auth, user, ticket, solution, knowledge, reward, notification, api-gateway |

---

## What's inside
```
com.library.common
├── application
│   └── usecase/          ← UseCase<I,O> base marker interface
├── constants/            ← AppConstants, Constants, KafkaTopics
├── domain/               ← BaseEntity, RepositoryPort
├── dto/                  ← ApiResponse<T>, ErrorResponse, PageRequestDTO, PageResponseDTO
├── enums/                ← UserRole, TicketStatus, TicketDifficulty, SolutionStatus, NotificationType, RewardType, DifficultyLevel
├── events/               ← BaseEvent, TicketEvent, SolutionEvent, RewardEvent, NotificationEvent
├── exception/            ← ResourceNotFoundException, UnauthorizedException, BusinessException, ValidationException, GlobalExceptionHandler
├── infrastructure/config/← CommonLibraryConfig, KafkaConfig, KafkaPropertiesConfig, SwaggerConfig
└── util/                 ← JwtUtil, SecurityUtil, ValidationUtil, DateUtil, LoggingUtil, TraceUtil
```

---

## Why it exists
Before extracting this module, each microservice had its own copy of:
- `ApiResponse<T>` wrapper (`{success, message, data}`) — the standard HTTP envelope across the stack
- Kafka topic names (magic strings that silently drifted)
- `JwtUtil` (the HS384 key was duplicated 7×, a nightmare to rotate)
- Role enum, status enums
- Global exception handler boilerplate

Consolidating them here means a single PR rolls out a consistent change.

---

## Key shared classes

### `dto.ApiResponse<T>`
Standard JSON envelope for every REST response:
```json
{ "success": true, "message": "ok", "data": { … } }
```

### `dto.PageRequestDTO` / `PageResponseDTO`
Uniform pagination contract: `{page, size, sort}` in → `{content, totalElements, totalPages, pageNumber, pageSize}` out.

### `util.JwtUtil`
HS384 sign / verify / extract-claims helpers. All services inject the shared secret via `jwt.secret` so tokens minted by `auth-service` verify everywhere.

### `constants.KafkaTopics`
| Constant | Topic |
|---|---|
| `TICKET_EVENTS` | `ticket-events` |
| `SOLUTION_EVENTS` | `solution-events` |
| `REWARD_EVENTS` | `reward-events` |
| `NOTIFICATION_EVENTS` | `notification-events` |
| `NOTIFICATIONS` | `notifications` |
| `USER_REGISTRATION` | `user-registration` |
| `SERVICE_AUDIT` | `service-audit` |
| `DLQ_TICKET_EVENTS` | `dlq-ticket-events` |
| `DLQ_NOTIFICATION_EVENTS` | `dlq-notification-events` |

> Note: most services now use their own `app.kafka.topics.*` yaml keys (e.g. `ticket.created`, `ticket.resolved`) for finer-grained topics. `KafkaTopics` holds the coarser channel names and DLQ constants.

### `enums.UserRole`
`ADMIN`, `MANAGER`, `ENGINEER`, `USER` — matches JWT `role` claim, `X-User-Role` gateway header, and `roles` table in `auth_db`.

### `events.*Event`
POJO shapes for every Kafka payload, so producer + consumer share a typed contract.

### `exception.GlobalExceptionHandler`
`@ControllerAdvice` that maps shared exceptions → `ErrorResponse` with correct HTTP status:
- `ResourceNotFoundException` → 404
- `UnauthorizedException` → 401
- `BusinessException` → 400
- `ValidationException` → 422

Services that need custom error mapping define their own `GlobalExceptionHandler` which overrides this one (Spring orders `@ControllerAdvice` by `@Order`).

---

## Build & install
```bash
cd common_library_service
./mvnw clean install         # installs to ~/.m2/repository/com/kva/common-library/1.0.0
```

From a service's `pom.xml`:
```xml
<dependency>
  <groupId>com.kva</groupId>
  <artifactId>common-library</artifactId>
  <version>1.0.0</version>
</dependency>
```

Then in the service's main class (optional — autoconfig wires most beans):
```java
@SpringBootApplication
@Import(com.library.common.infrastructure.config.CommonLibraryConfig.class)
public class MyServiceApplication { … }
```

---

## Testing
```bash
./mvnw test                  # ≈26 unit tests, JUnit 5 + Mockito
```
JaCoCo reports land in `target/site/jacoco/index.html`. Floor is 50% line / 40% branch.

---

## Versioning
- `1.0.0` — current, Java 21 / Spring Boot 3.3.5.
- Bumped whenever a shared DTO / enum / topic constant changes. Consumers pin the version in their `pom.xml` and rebuild.

---

## Troubleshooting

**Service fails startup with `ClassNotFoundException: com.library.common.dto.ApiResponse`**
You haven't installed the library JAR locally. Run `./mvnw clean install` inside `common_library_service/` first, then rebuild the service.

**JWT from auth-service fails to verify on another service**
The `JwtUtil` here signs HS384 with `jwt.secret`. Every service must set the **same** `jwt.secret`. Check `application.yaml` / `JWT_SECRET` env var consistency.

**Bean conflict on `KafkaTemplate`**
`KafkaConfig` provides a default. If your service defines its own, add `@Primary` to yours or exclude the autoconfig.

---

## Tech stack
- Java 21 (Temurin)
- Spring Boot 3.3.5 (BOM / starter)
- Spring Security (JJWT 0.11.5)
- Spring Kafka (producer + consumer helpers)
- springdoc-openapi-starter-webmvc-ui 2.6.0
- Lombok 1.18.34
- Jakarta Validation (jakarta.validation-api)
