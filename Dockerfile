FROM maven:3.8.4-eclipse-temurin-17 AS build
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN mvn clean install

FROM eclipse-temurin:17
RUN mkdir /app
COPY --from=build /src/target/*.jar /app/app.jar

WORKDIR /app

EXPOSE 5000 9090

ENTRYPOINT ["java", "-jar", "app.jar"]
