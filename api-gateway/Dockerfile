# ---------- BUILD STAGE ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos primero el POM para aprovechar la caché de Maven
COPY pom.xml .
# Descargamos las dependencias
RUN mvn -B dependency:go-offline

# Copiamos el resto del código fuente
COPY src ./src

# Construimos el JAR
RUN mvn -q package -DskipTests

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiamos el JAR construido
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
