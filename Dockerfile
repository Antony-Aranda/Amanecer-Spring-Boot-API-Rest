# Dockerfile
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copia pom y descarga dependencias (caché)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el resto del proyecto
COPY src ./src

# Compila y empaqueta
RUN mvn clean package -DskipTests

# Exponer el puerto que usa Spring Boot por defecto
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java","-jar","target/blog-api-0.0.1-SNAPSHOT.jar"]
