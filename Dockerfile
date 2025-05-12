# ---------- STAGE 1: Build con Maven + JDK 21 -----------
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# 1) Copia pom.xml y cachea dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B               # descarga todas las dependencias

# 2) Copia código y empaqueta JAR sin tests
COPY src ./src
RUN mvn clean package -DskipTests             # compila con --release 21

# ---------- STAGE 2: Runtime con Amazon Corretto 21 Alpine -----------
FROM amazoncorretto:21-alpine-jdk AS runtime
WORKDIR /app

# Copia el JAR empaquetado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
