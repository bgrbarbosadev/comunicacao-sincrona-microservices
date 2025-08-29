FROM maven:3.8.5-openjdk-11 AS builder

WORKDIR /app/ms-convidados
COPY ./ms-convidados ./

RUN mvn clean package -DskipTests


FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/ms-convidados/target/*.jar ./app.jar
ENTRYPOINT java -jar app.jar