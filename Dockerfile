# Imagen base con Maven y Java 17 para construir la aplicación
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copiar el código fuente y compilar
COPY pom.xml . 
COPY src ./src
RUN mvn clean package -DskipTests

# Usar un servidor Tomcat como base
FROM tomcat:9.0-jdk17

WORKDIR /usr/local/tomcat/webapps/

# Copiar el .war generado en la fase de construcción
COPY --from=build /app/target/Holos-0.0.1-SNAPSHOT.war ./ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
