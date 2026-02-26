# sql-data-service

Develop Spring Boot Backend to manage SQL data

## Generate JPA entities from PostgreSQL schema

1. Edit [src/main/resources/hibernate-codegen.properties](src/main/resources/hibernate-codegen.properties) with your PostgreSQL host, port, database, username, password, and schema.
2. Run:

```bash
./mvnw -Pcodegen generate-sources
```

Generated classes are written to `target/generated-sources/hibernate`.

The `codegen` Maven profile also adds that directory as a compile source root, so you can import generated entities directly in your code after running generation.

If your IDE does not immediately show them, refresh the Maven project (VS Code: **Maven: Reload projects**)
