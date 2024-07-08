# ControlClick Backend

Este é o backend do projeto ControlClick, uma aplicação para gerenciamento de usuários. O backend é construído usando Spring Boot e inclui um banco de dados H2 para armazenamento de dados.

## Pré-requisitos

Certifique-se de ter os seguintes itens instalados em seu ambiente de desenvolvimento:

- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

## Inicializando o Projeto

Siga os passos abaixo para configurar e iniciar o projeto.

### 1. Clone o Repositório
git clone https://github.com/seu-usuario/control-click-backend.git
cd control-click-backend

### 2. Rodar a Aplicação
Para rodar a aplicação, execute o seguinte comando no console:

Copiar código
./mvnw spring-boot:run
A aplicação será iniciada e estará disponível em http://localhost:8080.

### 3. Acessar o Banco de Dados
O projeto utiliza um banco de dados H2. Para acessar a interface do banco de dados, vá até a seguinte URL no seu navegador:
http://localhost:8080/h2-console

### 4. Credenciais do Banco de Dados
As credenciais e configurações do banco de dados podem ser encontradas no arquivo application.properties, localizado em:
src/main/resources/application.properties
