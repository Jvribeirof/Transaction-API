FROM maven:3.9.11-eclipse-temurin-17-alpine AS build
COPY . /app
WORKDIR /app
RUN mvn clean package

FROM eclipse-temurin:17-alpine
COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar","app.jar"]