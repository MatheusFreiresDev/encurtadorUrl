# 🔗 Encurtador de URLs

API REST para encurtar URLs longas, com redirecionamento automático e prazo de validade.

---

## 🚀 Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate Validator
- Lombok
- MySQL

---

## 📋 Funcionalidades

- ✅ Encurta URLs longas em códigos de 6 caracteres
- ✅ Reutiliza o código caso a URL já tenha sido encurtada anteriormente
- ✅ Redireciona para a URL original ao acessar o link encurtado
- ✅ Prazo de validade de 5 dias por link
- ✅ Validação de formato de URL
- ✅ Tratamento de erros padronizado

---

## ⚙️ Como rodar

### Pré-requisitos
- Java 17+
- MySQL rodando localmente

### Configuração do banco

No arquivo `src/main/resources/application.yaml`, configure suas credenciais:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/encurtador
    username: seu_usuario
    password: sua_senha
  jpa:
    hibernate:
      ddl-auto: update
```

### Rodando o projeto

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

---

## 📡 Endpoints

### Encurtar URL
```http
POST /encurtar
Content-Type: application/json

{
  "url": "https://seusite.com.br/pagina-muito-longa"
}
```

**Resposta:**
```json
HTTP 200 OK

{
  "url": "http://localhost:8080/a1b2c3"
}
```

---

### Redirecionar para URL original
```http
GET /{shortUrl}
```

**Resposta:** `HTTP 302 Found` — redireciona automaticamente para a URL original.

---

## ❌ Erros

Todos os erros seguem o padrão:

```json
{
  "timestamp": "2024-02-24T10:30:00",
  "status": 404,
  "erro": "Not Found",
  "message": "Url não encontrada.",
  "patch": "/uri-da-requisicao"
}
```

| Status | Situação |
|--------|----------|
| `400 Bad Request` | URL com formato inválido |
| `404 Not Found` | Link encurtado não existe no banco |
| `410 Gone` | Link encurtado expirou |

---

## 🗂️ Estrutura do projeto

```
src/main/java/com/encurtador/
├── controllers/
│   └── UrlController.java
├── service/
│   └── UrlService.java
├── repositorys/
│   └── UrlRepository.java
├── Entity/
│   └── Url.java
├── dtos/
│   ├── UrlDto.java
│   └── ExceptionResponse.java
├── execeptions/
│   ├── UrlNotFounded.java
│   └── UrlExpired.java
└── configSecurity/
    └── GlobalExceptionHandler.java
```
