# TODO: Refactor to Clean Architecture Common Library JAR

## Approved Plan Steps

1. [x] Update pom.xml: names, Java 17, packaging jar, strip unnecessary deps (keep validation/lombok), add modules if needed. Verified with ./mvnw clean compile - SUCCESS.

2. [x] Rename package dirs: com.example.common_library_service → com.library.common.
3. [x] Remove unnecessary resources: static/, templates/, db/changelog/.
4. [x] Refactor main app class: remove @SpringBootApplication, move to config in infrastructure.
5. [x] Update application.yaml: minimal library config.
6. [x] Update tests: packages and structure.
7. [x] Create clean arch layers: domain (entities/ports), application (usecases), infrastructure (adapters), common (utils).
8. [x] Validate: mvn clean package - SUCCESS. JAR ready for use.

9. [ ] Finalize: git add/commit.

Progress will be updated here.
