# API - ORDER - TRABALHO INFNET

Projeto conceito para utilização de Serviço Rest com Spring Boot

## Iniciando o Projeto

O projeto contém alguns exemplos de serviço REST. O projeto já está configurado em modo standalone

### Pré requisitos

```
Java 17 : ([https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html]);

Spring Boot na versão 3.1.9:  (https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/3.1.9);

Maven 3.9.5: (https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz)

Eclipse ou Intellij

Docker: Para Rodar o arquivo docker-compose (Contido no projeto principal) que contém a imagem do mysql para facilitar a integração

Postman para Testes : (https://www.postman.com/downloads/)
```

## Swagger

Para acessar a documentação do Swagger, utilize o link abaixo (Local):

```
http://localhost:8083/swagger-ui/index.html
```

## Rodando os Testes

Utilize o postman Para rodar os testes.

Collection estão na raiz do repositório.


```
Shoes.click.postman_collection.json
```

## Rodando local

Inicie com a classe Application.java

## Deploy

Basta executar o comando maven install

Para rodar, vá na pasta target onde tem o artefato gerado e execute o comando:

```
java -jar api--order-0.0.1-SNAPSHOT.jar
```


## Autores

* **Clayton Morais de Oliveira**
