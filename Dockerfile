FROM maven:3.8.4-eclipse-temurin-17 AS build
RUN mkdir /src
COPY . /src
WORKDIR /src
RUN mvn clean install

FROM eclipse-temurin:17
RUN mkdir /app
COPY --from=build /src/target/*.jar /app/app.jar

WORKDIR /app

ENV JAVA_OPTS "$JAVA_OPTS \
    -Duser.timezone=America/Fortaleza \
    -XX:ActiveProcessorCount=2 \
    -XX:+UseParallelGC \
    -XX:MaxRAMPercentage=75"

EXPOSE 5000 9190

ENTRYPOINT ["sh","-c", "java ${JAVA_OPTS} -jar app.jar"]
