# Use uma imagem base com o JDK
FROM openjdk:21-jdk

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR da aplicação para o contêiner
COPY target/ferias-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação vai usar
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
