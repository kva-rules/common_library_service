FROM maven:3.9.9-eclipse-temurin-17 as builder

WORKDIR /app

# Copy pom and download deps
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage - just extract JAR artifact (no app server needed)
FROM eclipse-temurin:17-jre-alpine as artifact

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

# Extract plain classes/JAR for dependency (no BOOT-JAR fat jar needed)
RUN java -Djarmode=layertools -jar app.jar extract

CMD ["echo", "Common library JAR built. Use target/*.jar or extract dependencies/layers/dependencies/*.jar"]

