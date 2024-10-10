FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

# Build do projeto com Maven
RUN ./mvnw package

# Comando para executar o JAR (ajuste o caminho se necessário)
# USAR MVN CLEAN PACKAGE PARA CRIAR O NOVO .JAR
CMD ["java", "-jar", "target/a3-backend-0.0.1-SNAPSHOT.jar"]