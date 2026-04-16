# Common Library Module

Reusable Spring Boot library JAR for microservices and API Gateway. Follows clean architecture.

## Purpose

Shared utilities, DTOs, exceptions, enums, Kafka events, constants for consistent:

- Validation & security
- Pagination & tracing
- Error handling & domain models
- Event-driven communication

## Usage

```
mvn clean install
```

JAR: `target/common-library-0.0.1-SNAPSHOT.jar`

### Maven Dependency

```xml
<dependency>
  <groupId>com.library.common</groupId>
  <artifactId>common-library</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Integration with Microservices

```
@SpringBootApplication
@Import(com.library.common.infrastructure.config.CommonLibraryConfig.class)
public class MyServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(MyServiceApplication.class, args);
  }
}
```

- Use DTOs: `PageRequestDTO`, `ApiResponse`
- Events: `TicketEvent`
- Utils: `ValidationUtil.validateUUID(id)`
- Exceptions: `ResourceNotFoundException`
- Enums: `UserRole.ADMIN`
- Constants: `AppConstants.DEFAULT_PAGE_SIZE`

## CI/CD

GitHub Actions (`.github/workflows/ci.yml`):

- JDK 17
- mvn clean package test
- Artifact upload

## Testing

26 unit tests (JUnit5/Mockito):

- `./mvnw test`
  JaCoCo coverage (`target/site/jacoco/index.html`):
- 53.9% lines, 65% branches (min 50%/40%)
- Enforced check

## Modules

- **domain**: BaseEntity, ports
- **application**: UseCases
- **infrastructure**: Configs (Kafka/Swagger)
- **common**: DTOs/events/enums/utils/constants

## Build JAR

`./mvnw clean package`
