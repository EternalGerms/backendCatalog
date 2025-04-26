# Usa a imagem oficial do OpenJDK 17 com Maven
FROM maven:3.8.4-openjdk-17

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o pom.xml
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:resolve

# Copia o código fonte
COPY src ./src

# Compila e executa o app
CMD ["mvn", "spring-boot:run"]

# Se quiser usar o .jar gerado (para produção), substitua o CMD por:
# RUN ./mvnw package
# COPY target/*.jar app.jar
# CMD ["java", "-jar", "app.jar"]