# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-17 AS build
ENV DATABASE_URL = "jdbc:postgresql://host.docker.internal:5432/validador"
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
# Ejecutar la aplicación
CMD ["mvn", "spring-boot:run"]