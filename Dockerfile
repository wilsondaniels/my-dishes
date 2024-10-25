# Use a imagem base do Java 21
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /usr/app

# Copia o arquivo .jar da pasta target do host para o container
COPY target/my-dishes-0.0.1-SNAPSHOT.jar /usr/app/app.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "/usr/app/app.jar"]
