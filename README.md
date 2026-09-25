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

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git](https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git)
   cd NOME_DO_REPOSITORIO
