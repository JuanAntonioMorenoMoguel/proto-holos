# Usar Maven con OpenJDK 17 para construir la aplicación
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copiar archivos y compilar el proyecto con el perfil Docker
COPY pom.xml . 
COPY src ./src
RUN mvn clean package -DskipTests -Pdocker

# Imagen base para ejecutar el JAR
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el JAR compilado
COPY --from=build /app/target/Holos-0.0.1-SNAPSHOT.jar .

# Copiar archivos JSP y recursos
COPY src/main/webapp/ /app/webapp/

# Exponer el puerto de la aplicación
EXPOSE 8080

# Ejecutar la aplicación con el perfil Docker
ENTRYPOINT ["java", "-jar", "/app/Holos-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]
