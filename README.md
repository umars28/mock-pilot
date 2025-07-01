# 🧪 Mockery Spring – Dynamic Mock API Server with Spring Boot

Mockery is a lightweight and dynamic mock API server built with Spring Boot.  
It allows developers to register and simulate custom HTTP endpoints **at runtime** — ideal for frontend development, API prototyping, and contract testing.

---

## 🚀 Features

- ✅ Create mock endpoints via REST API
- 🛠️ Support for dynamic method + path (e.g. `/users/:id`)
- 💾 In-memory storage for mock configuration (can be extended to DB)
- 📦 Simple JSON-based request/response definitions
- 🔍 Match any path using basic `:param` support

---

## 📌 Use Case Examples

- Frontend devs testing without waiting for real backend
- QA mocking edge cases or API contracts
- Quick API prototyping with no database

---

### 1. Run the server

```bash
./mvnw spring-boot:run
```

### 2. Register a mock endpoint

To register a new mock endpoint dynamically, send a `POST` request to `/__mock` with a JSON body that defines:

- HTTP method
- Path (can include `:params`)
- Response status and body

```bash
curl -X POST http://localhost:8080/__mock \
  -H "Content-Type: application/json" \
  -d '{
    "method": "GET",
    "path": "/hello",
    "response": {
      "status": 200,
      "body": {
        "message": "Hello, World!"
      }
    }
  }'
```

### 3. Call the mocked endpoint
Once registered, the endpoint becomes immediately available without restarting the server:
```bash
curl http://localhost:8080/hello
```
Expected Output
```json
{
  "message": "Hello, World!"
}
```

### Advanced: Mocking Path Parameters
```bash
curl -X POST http://localhost:8080/__mock \
  -H "Content-Type: application/json" \
  -d '{
    "method": "GET",
    "path": "/users/:id",
    "response": {
      "status": 200,
      "body": {
        "id": 42,
        "role": "admin"
      }
    }
  }'
```
```bash
curl http://localhost:8080/users/42
```
Expected Output
```json
{
  "id": 42,
  "role": "admin"
}
```
