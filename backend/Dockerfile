# Dev-only Dockerfile
FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app

# Copy pom.xml and download dependencies first (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Default command runs Spring Boot in dev mode
CMD ["mvn", "spring-boot:run"]
