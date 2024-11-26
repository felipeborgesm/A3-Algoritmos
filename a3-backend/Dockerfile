FROM openjdk:17-jdk-alpine

WORKDIR /app

# Adicionar usuário não-root
RUN addgroup -S spring && adduser -S spring -G spring

# Instalar dependências necessárias
RUN apk add --no-cache bash

# Criar diretório do banco de dados e ajustar permissões
RUN mkdir -p /app/database && \
    chown -R spring:spring /app && \
    chmod 777 /app/database

# Copiar arquivos do projeto
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw && \
    ./mvnw dependency:go-offline

COPY src ./src

# Build do projeto
RUN ./mvnw package -DskipTests

# Mudar para usuário não-root
USER spring:spring

# Comando para executar
CMD ["java", "-jar", "target/a3-backend-0.0.1-SNAPSHOT.jar"]