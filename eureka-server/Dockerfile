# ---------- BUILD STAGE ----------
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Instalamos Maven
RUN apt-get update && apt-get install -y maven

# Copiamos TODO el proyecto
COPY . .

# Construimos el JAR
RUN mvn -q package -DskipTests

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]
