# 📌 API REST - Mural de Vagas

API RESTful desenvolvida em Spring Boot para a gestão de vagas de emprego e utilizadores, com suporte a persistência em PostgreSQL e documentação interativa gerada via OpenAPI / Swagger UI.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.5**
    - Spring Data JPA
    - Spring Web
    - Spring Validation
- **PostgreSQL** (Base de Dados Relacional)
- **SpringDoc OpenAPI 2.6.0** (Swagger UI)
- **Lombok**
- **Gradle**

---

## ⚙️ Funcionalidades Principalmente Mapeadas

### 💼 Vagas (`/api/vagas`)
- `GET /api/vagas` - Listagem de todas as vagas
- `GET /api/vagas/{id}` - Detalhes de uma vaga específica
- `POST /api/vagas` - Registo de nova vaga
- `PUT /api/vagas/{id}` - Atualização dos dados da vaga
- `DELETE /api/vagas/{id}` - Remoção de vaga

### 👤 Utilizadores (`/api/usuarios`)
- `GET /api/usuarios/{id}` - Consulta de utilizador por ID
- `PUT /api/usuarios/{id}` - Atualização de dados do utilizador
- `DELETE /api/usuarios/{id}` - Remoção de utilizador

---

## 📋 Pré-requisitos

Antes de iniciar a aplicação, certifique-se de que tem instalado no seu ambiente:
- **JDK 17** ou superior
- **PostgreSQL** a correr localmente (ou via Docker)
- **Git**

---

## 🛠️ Como Executar o Projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/JLMeirelesjr/api-rest-mural-vagas.git
cd api-rest-mural-vagas
```

### 2. Configurar o banco de dados

Crie o banco PostgreSQL local (nome usado pelo projeto: `db_muralvagas`):

```bash
docker run --name postgres-mural-vagas \
  -e POSTGRES_DB=db_muralvagas \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=1234 \
  -p 5432:5432 \
  -d postgres:16
```

> As credenciais já vêm configuradas em `src/main/resources/application.properties`. Se seu PostgreSQL local usar usuário/senha diferentes, ajuste esse arquivo antes de rodar.

O Hibernate está configurado com `ddl-auto=update`, então as tabelas são criadas automaticamente a partir das entidades `@Entity` — não é necessário rodar script SQL manual.

### 3. Rodar a aplicação

No Windows:
```bash
gradlew.bat bootRun
```

No Linux/Mac:
```bash
./gradlew bootRun
```

A aplicação sobe por padrão em `http://localhost:8080`.

> Se der erro de porta em uso, libere a porta com `netstat -ano | findstr :8080` seguido de `taskkill /PID <PID> /F`, ou troque a porta no `application.properties` com `server.port=8081`.

### 4. Acessar a documentação (Swagger)

```
http://localhost:8080/swagger-ui.html
```

### 5. Testar os endpoints

Base da URL:
```
http://localhost:8080/api
```

Exemplo: `GET http://localhost:8080/api/vagas`