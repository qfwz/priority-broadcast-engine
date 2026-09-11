# --- Build stage ---
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B clean package -DskipTests

# --- Runtime stage ---
FROM payara/micro:5.2022.5-jdk11
COPY --from=build /app/target/priority-broadcast.war $DEPLOY_DIR
EXPOSE 8080