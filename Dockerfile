FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY pom.xml .
COPY src ./src

RUN mvn -B -DskipTests clean package

# Copy the packaged Spring Boot jar to a stable path for the runtime image.
RUN cp "$(find target -maxdepth 1 -type f -name '*.jar' ! -name '*.jar.original' | head -n 1)" /workspace/app.jar

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /workspace/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]