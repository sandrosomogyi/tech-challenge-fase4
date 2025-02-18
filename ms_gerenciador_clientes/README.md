# MS Gerenciador de Clientes

Este é o microsserviço de Gerenciamento de Clientes desenvolvido como parte do Tech Challenge Fase 4 da FIAP.

## Requisitos

- Java 17
- Maven 3.9.9
- MySQL

## Configuração do Banco de Dados

Certifique-se de que você tenha um banco de dados MySQL rodando e crie um banco de dados chamado `gerenciamento_clientes`. Atualize as credenciais do banco de dados no arquivo `src/main/resources/application-dev.properties` substituindo o username e password.

```properties
server.port=8082

spring.datasource.url=jdbc:mysql://127.0.0.1:3306/gerenciamento_clientes
spring.datasource.username={username}
spring.datasource.password={password}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Executando o Projeto

### Usando o Maven Wrapper

1. Clone o repositório:
    ```sh
    git clone https://github.com/sandrosomogyi/tech-challenge-fase4.git
    cd ms_gerenciador_clientes
    ```

2. Compile e execute o projeto:
    ```sh
    ./mvnw spring-boot:run
    ```

### Usando o Maven Local

1. Clone o repositório:
    ```sh
    git clone https://github.com/sandrosomogyi/tech-challenge-fase4.git
    cd ms_gerenciador_clientes
    ```

2. Compile o projeto:
    ```sh
    mvn clean install
    ```

3. Execute o projeto:
    ```sh
    mvn spring-boot:run
    ```

### Acessando a Aplicação

Após iniciar a aplicação, ela estará disponível no seguinte endereço: http://localhost:8082
