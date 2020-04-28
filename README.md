# Exemplo de um Serviço REST utilizando o Spring Boot

Essa aplicação foi desenvolvida utilizando os conceitos da semana spring rest algaworks (20/04/2020 à 26/04/2020)

## Passos iniciais

Recomendo utilizar alguma IDE [Spring Tools](https://spring.io/tools).

### Pre-requisitos

```
- JDK 11
- MySQL
- Postman
- WorkBench
```

### Configurações Persistência

Para configurar o banco de dados com o java, abra o arquivo application.properties no diretório 
src/main/resourse/ e adicione as seguintes linhas

```
spring.datasource.url=jdbc:mysql://localhost:3306/osworks?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=nomedeusuario
spring.datasource.password=senhadobanco
```

### Configuração Flyway

crie o diretório src/main/resourse/db/migration para adicionar todas instruções de comandos SQL.
crie um arquivo com o nome "V001__cria-tabela-cliente.sql" 

####  Importante
Todo arquivo.sql deve seguir o padrão de nomenclatura V + o numero da versao + __ (2 underscore)+ o que a instrução faz.

Ex:

```
V001__o-que-essa-instrucao-faz.sql
V002__o-que-essa-instrucao-faz.sql
V003__o-que-essa-instrucao-faz.sql
V004__o-que-essa-instrucao-faz.sql
```

#### Instrução SQL para criar a tabela cliente
```
create table cliente (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(255) not null,
    telefone varchar(20) not null,
    
    primary key (id)
);
```

#### Instrução SQL para criar a tabela ordem de servico
```
create table ordem_servico(
	id bigint not null auto_increment,
    cliente_id bigint not null,
    descricao text not null,
    preco decimal(10,2) not null,
    status varchar(20) not null,
    data_abertura datetime not null,
    data_finalizacao datetime,
    
    primary key (id) 
);

alter table ordem_servico add constraint fk_ordem_servico_cliente 
foreign key (cliente_id) references cliente (id);
```

#### Instrução SQL para criar a tabela comentario
```
create table comentario(
	id bigint not null auto_increment,
    ordem_servico_id bigint not null,
	descricao text not null,
    data_envio datetime not null,
	
    primary key (id)
);

alter table comentario add constraint fk_comentario_ordem_servico
foreign key (ordem_servico_id) references ordem_servico (id);
```

## Recursos

* [HTTP_Status](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status) - Significado de códigos de status HTTP
* [Spring Data JPA](https://maven.apache.org/) - Para implementação de repositórios baseados JPA
* [ModelMapper](https://rometools.github.io/rome/) -  framework que realiza o mapeamento de objetos de forma inteligente
* [SpringBoot-devtools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html) -  ferramenta responsável pelo restart automático da aplicação a cada alteração.
* [Flyway Migration](https://flywaydb.org/) -  Controle de versão para banco de dados

