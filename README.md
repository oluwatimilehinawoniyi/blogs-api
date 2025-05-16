# 📝 Blog API

This is a simple Blog API built with **Spring Boot** to practice and learn
backend fundamentals such as REST APIs, service-layer design, DTOs, and
Spring Data JPA.

It's still under active development and currently includes only the **Blog
** feature. More features like **Users**, **Comments**, and **Tags** will
be added incrementally.

---

## 🧠 What I’m Learning

This project is part of my journey to backend engineering using
Java/Spring Boot. I’m focusing on:

- Spring Data JPA and entity relationships
- Clean service layer architecture
- REST API best practices
- DTO usage and request/response separation
- PostgreSQL integration with Spring

---

## 📦 Features (Implemented so far)

- ✅ Blog post CRUD endpoints

> Upcoming:
> - Users, Comments, Tags, Pagination, and Security

---

## 📃 To-Do List (Planned Features)

- [ ] Implement pagination and sorting
- [ ] Implement Comment feature
  - [x] CRUD feature
  - [ ] Only authorized users can create/update/delete comments
- [ ] Implement User feature
- [ ] Implement Authentication/Authorization using Spring Security
- [ ] Implement Tag feature (filter/search blogs by tag)
- [ ] Proper REST error handling
- [ ] Global exception handling
- [ ] Add Swagger/OpenAPI documentation
- [ ] Unit and integration tests

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3+
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/oluwatimilehinawoniyi/blogs-api
cd blogs-api
```

### 2. Setup Your Database

Ensure PostgreSQL is installed and running on your machine.

Create a database named blogs:

```sql
CREATE DATABASE blogs;
```

You can use PgAdmin, DBeaver, or CLI to do this.

### 3. Configure Your Environment Variables

You can provide the DB credentials by using a `.env` File

- Create an .env file in your project root:

    ```dotenv
    DB_USERNAME=your_postgres_username
    DB_PASSWORD=your_postgres_password
    ```
- In IntelliJ:
    - Go to Run > Edit Configurations
    - Select your run configuration (e.g., BlogsApplication)
        - Select "modify options"
        - Scroll down and Select "Environment Variables"
    - In the "Environment Variables" section, click the 📂 (file) icon
    - Select your .env file as the source
    - Save and run

This way, IntelliJ will load the .env file and inject the variables into
the app automatically.

### 4. Run the Application
Click the Green Play Arrow at the top of Intellij editor OR in your 
terminal, run:
```bash
./mvnw spring-boot:run
```

The app will start on:
`http://localhost:8080`

## 🔍 API Endpoints

These will evolve as the project grows, but for now:

`GET /api/v1/blogs — Get all blogs`

`GET /api/v1/blogs/{id} — Get a specific blog`

`POST /api/v1/blogs — Create a new blog`

`PUT /api/v1/blogs/{id} — Update a blog`

`DELETE /api/v1/blogs/{id} — Delete a blog`

## 🧪 Database Config

Example application.yml (already configured):

```yaml
spring:
  application:
    name: blogs
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/blogs
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: create-drop
    show-sql: true
  sql:
    init:
      mode: always
```

## 💻 Author

Oluwatimilehin Awoniyi — diving into backend development and loving every
bit of it.

## 📬 Contributing

This project is for personal learning, but PRs or feedback are welcome!