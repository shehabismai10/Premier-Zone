# 🏟️ Premier Zone - Backend API

## 📖 Description
**Premier Zone** is a high-performance Java Spring Boot application built to manage and analyze Premier League player statistics. Beyond simple data management, this system implements a **modern, event-driven architecture** with a robust **JWT-based security layer**, ensuring a scalable and secure experience for both users and administrators.

---

## 🚀 Key Features
* 🛡️ **Advanced RBAC & JWT**: Secure Token-based authentication with **Role-Based Access Control**. Granular permissions where `ADMIN` handles data modification (POST, PUT, DELETE) and `USER` has read-only access.
* 📦 **Data Integrity with DTOs**: Implemented the **DTO Pattern** to decouple API responses from database entities, enhancing security and preventing sensitive data leakage (like internal IDs or passwords).
* ⚡ **Event-Driven & Async**: High-performance user onboarding using **Spring Events** (`UserRegisteredEvent`) and `@Async` processing to keep the main thread responsive and fast.
* ⚽ **Comprehensive Player Stats**: RESTful endpoints to manage Goals, Assists, and Team data with optimized JPA queries for maximum stability.
* 📖 **Interactive API Docs**: Integrated **Swagger/OpenAPI 3**. Explore and test the API at:  
  `http://localhost:8081/swagger-ui/index.html`
* 🏗️ **Decoupled Architecture**: Clean separation between Controllers, Services, Repositories, and Asynchronous Notification Listeners.

---

## 🛠️ Technologies Used
* **Java 21/25** (LTS)
* **Spring Boot 3.4.3**
* **Spring Security & JWT**
* **Spring Data JPA & MySQL**
* **Maven** (Dependency Management)
* **Swagger/OpenAPI** (Documentation)

---

## 🔌 API Documentation

### 🔐 Authentication Endpoints
**Base URL:** `http://localhost:8081/api/v1/auth`

**1. Register User (`POST /register`)**
```json
{
  "username": "shehab_dev",
  "email": "shehab@example.com",
  "password": "password123"
}
2. Login (POST /login)

JSON
{
  "email": "shehab@example.com",
  "password": "password123"
}
Response: { "token": "eyJhbGci..." }

⚽ Player Endpoints (Bearer Token Required)
Base URL: http://localhost:8081/api/v1/players

GET / - Fetch all players (Accessible by USER & ADMIN).

GET /{id} - Fetch player by ID.

POST / - Add a new player (Admin Only).

PUT /{id} - Update player details (Admin Only).

DELETE /{id} - Remove a player (Admin Only).

📂 Project Structure
Plaintext
Premier-Zone/
├── src/
│   ├── main/
│   │   ├── java/com/pl/premier_zone/
│   │   │   ├── auth/         # JWT Security & Auth Controllers
│   │   │   ├── dto/          # Request/Response Data Transfer Objects ✅
│   │   │   ├── config/       # Security, JWT & Async Configurations
│   │   │   ├── user/         # User Entities & Repositories
│   │   │   ├── player/       # Player Business Logic & Services
│   │   │   ├── event/        # Custom Application Events
│   │   │   └── notification/ # Async Event Listeners & Email Logic
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
🛠️ Installation & Setup
Clone the repository:

Bash
git clone [https://github.com/shehabismai10/Premier-Zone.git](https://github.com/shehabismai10/Premier-Zone.git)
cd Premier-Zone
Database Configuration:
Update src/main/resources/application.properties with your MySQL credentials.

Build and Run:

Bash
mvn clean spring-boot:run
👨‍💻 Developer Note
This project has been optimized to handle high-frequency requests by offloading non-critical tasks (like welcome notifications) to background threads using Spring's Task Executor. The use of DTOs and RBAC ensures the system follows industry-standard security and architectural patterns.

Developed by Shehab Ismail
