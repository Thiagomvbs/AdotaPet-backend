# 🐾 AdotaPet - Backend

API REST desenvolvida com Java e Spring Boot para gerenciamento de adoção de pets.

O sistema permite o cadastro e gerenciamento de Pets, Tutores e Adoções, disponibilizando operações CRUD completas através de endpoints REST e persistência em banco de dados relacional.

---

# 📖 Sobre o Projeto

O AdotaPet foi desenvolvido como trabalho da disciplina de Sistemas Distribuídos e Mobile.

O objetivo do projeto é demonstrar os conceitos de:

* Arquitetura Cliente-Servidor
* Comunicação via HTTP
* API REST
* Persistência de Dados
* Integração Frontend e Backend
* Testes de API utilizando Postman

---

# 🛠 Tecnologias Utilizadas

## Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* Lombok

## Banco de Dados

* MySQL

## Ferramentas

* Postman
* Git
* GitHub
* IntelliJ IDEA

---

# 📂 Estrutura do Projeto

```text
src
 └── main
      ├── java
      │    └── com.adocao.pet.api
      │         ├── controller
      │         ├── service
      │         ├── repository
      │         ├── entity
      │         ├── dto
      │         ├── exception
      │         ├── config
      │         └── enums
      │
      └── resources
           └── application.properties
```

O projeto segue uma arquitetura em camadas:

* Controller → Recebe as requisições HTTP.
* Service → Contém as regras de negócio.
* Repository → Responsável pelo acesso aos dados.
* Entity → Representa as tabelas do banco de dados.
* DTO → Objetos de entrada e saída da API.
* Exception → Tratamento centralizado de erros.
* Config → Configurações da aplicação.
* Enums → Enumerações utilizadas pelo sistema.

---

# 🐶 Entidades

## Pet

Representa os animais disponíveis para adoção.

Campos principais:

* id
* nome
* espécie
* raça
* idade
* descrição
* status

---

## Tutor

Representa os usuários interessados em adotar pets.

Campos principais:

* id
* nome
* email
* cpf
* telefone

---

## Adoção

Representa uma solicitação de adoção realizada por um tutor.

Campos principais:

* id
* dataSolicitacao
* status
* tutor
* pet

### Fluxo da adoção

1. O tutor solicita uma adoção.
2. A solicitação é criada com status PENDENTE.
3. O administrador pode APROVAR ou RECUSAR.
4. Quando aprovada, o pet passa a ser considerado adotado.

---

# 🚀 Como Executar o Projeto

## 1. Clonar o Repositório

```bash
git clone https://github.com/SEU-USUARIO/AdotaPet-Backend.git
```

---

## 2. Criar o Banco de Dados

```sql
CREATE DATABASE adota_pet;
```

---

## 3. Configurar o application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/adota_pet
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 4. Executar a Aplicação

```bash
mvn spring-boot:run
```

ou execute diretamente pela IDE.

---

# 🔗 Endpoints Disponíveis

## Pets

| Método | Endpoint   | Descrição         |
| ------ | ---------- | ----------------- |
| GET    | /pets      | Listar pets       |
| GET    | /pets/{id} | Buscar pet por ID |
| POST   | /pets      | Cadastrar pet     |
| PUT    | /pets/{id} | Atualizar pet     |
| DELETE | /pets/{id} | Remover pet       |

---

## Tutores

| Método | Endpoint      | Descrição           |
| ------ | ------------- | ------------------- |
| GET    | /tutores      | Listar tutores      |
| GET    | /tutores/{id} | Buscar tutor por ID |
| POST   | /tutores      | Cadastrar tutor     |
| PUT    | /tutores/{id} | Atualizar tutor     |
| DELETE | /tutores/{id} | Remover tutor       |

---

## Adoções

| Método | Endpoint              | Descrição            |
| ------ | --------------------- | -------------------- |
| GET    | /adocoes              | Listar adoções       |
| GET    | /adocoes/{id}         | Buscar adoção por ID |
| POST   | /adocoes              | Solicitar adoção     |
| PUT    | /adocoes/{id}/aprovar | Aprovar adoção       |
| PUT    | /adocoes/{id}/recusar | Recusar adoção       |
| DELETE | /adocoes/{id}         | Remover adoção       |

---

# 📮 Testes com Postman

Todos os endpoints foram testados utilizando o Postman.

## Collection

Disponível em:

```text
postman/AdotaPet.postman_collection.json
```

Ou diretamente:

```text
https://github.com/SEU-USUARIO/AdotaPet-Backend/blob/main/postman/AdotaPet.postman_collection.json
```

---

# 📷 Evidências dos Testes

## Cadastro de Pet

![Cadastro de Pet](images/postman/post-pet.png)

---

## Atualização de Pet

![Atualização de Pet](images/postman/put-pet.png)

---

## Exclusão de Pet

![Exclusão de Pet](images/postman/delete-pet.png)

---

## Cadastro de Tutor

![Cadastro de Tutor](images/postman/post-tutor.png)

---

## Atualização de Tutor

![Atualização de Tutor](images/postman/put-tutor.png)

---

## Solicitação de Adoção

![Solicitação de Adoção](images/postman/post-adocao.png)

---

## Aprovação de Adoção

![Aprovação de Adoção](images/postman/aprovar-adocao.png)

---

## Recusa de Adoção

![Recusa de Adoção](images/postman/recusar-adocao.png)

---

# 📁 Estrutura Recomendada para Evidências

```text
AdotaPet-Backend
│
├── images
│   └── postman
│       ├── post-pet.png
│       ├── put-pet.png
│       ├── delete-pet.png
│       ├── post-tutor.png
│       ├── put-tutor.png
│       ├── post-adocao.png
│       ├── aprovar-adocao.png
│       └── recusar-adocao.png
│
├── postman
│   └── AdotaPet.postman_collection.json
│
└── README.md
```

---

# ✅ Requisitos Atendidos

* API REST desenvolvida com Spring Boot
* CRUD completo
* Persistência de dados com MySQL
* Arquitetura em camadas
* Retorno em JSON
* Tratamento de erros
* Testes utilizando Postman
* Integração com Frontend
* Comunicação via HTTP

---

# 👨‍💻 Autor

Thiago Monteiro Villas

Projeto desenvolvido para a disciplina de Sistemas Distribuídos e Mobile.
