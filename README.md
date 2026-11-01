# person-service

Java 25, Spring Boot 4.1, Gradle, Flyway and PostgreSQL.

| Task | Command |
|---|---|
| Build and run all tests and checks | `./gradlew build` |
| Run only the module dependency check | `./gradlew verifyModuleDependencies` |
| Run only the end-to-end tests | `./gradlew integrationTest` |
| Start the local database | `docker compose up -d` |
| Run the service | `./gradlew :app:bootRun` |

Swagger UI: http://localhost:8080/swagger-ui.html

## Add a feature

`person` is a sample feature. Copy its files and rename them, or follow this list. For a feature called `address`:

1. **`domain`:** add `domain/address/` with `Address`, `AddressRepository`, `AddressService` and a unit test.
2. **`persistence`:** add `persistence/address/` with `AddressEntity`, `AddressJpaRepository`, `AddressRepositoryImpl`, `AddressMapper` and a repository test.
3. **Migration:** add the next numbered file in `persistence/src/main/resources/db/migration`, such as `V3__create_addresses_table.sql`.
4. **`api`:** add `api/address/` with `AddressController`, `AddressRequest`, `AddressResponse`, `AddressApiMapper` and a controller test.
5. **`app`:** add an end-to-end test, `integration/AddressEndToEndIT`.
6. Run `./gradlew build`.

## Remove the sample feature

Delete every `person` folder in the five modules, along with `integration/PersonEndToEndIT` and `V2__create_persons_table.sql`. The build still passes without them.

Only delete a migration if it has never been applied to a shared database. After that, add a new migration to drop the table instead.

## Module dependencies

The allowed dependencies between modules are in `dependencies.txt`. Change that file and the module's `build.gradle` together, or the build fails.
