
# 🏟️ Premier Zone - Backend API

## 📖 Description
**Premier Zone** is a high-performance Java Spring Boot application built to manage and analyze Premier League player statistics. Beyond simple data management, this system implements a **modern, event-driven architecture** with a robust **JWT-based security layer**, ensuring a scalable and secure experience for both users and administrators.

---

## 🚀 Key Features
* 🛡️ **Secure Auth & RBAC**: Full JWT Authentication (Register/Login) with managed access for `USER` and `ADMIN` roles.
* ⚡ **Event-Driven Registration**: Implements an asynchronous notification system. When a user registers, a `UserRegisteredEvent` is fired and handled in the background.
* ⚽ **Comprehensive Player Stats**: RESTful endpoints to manage Goals, Assists, and Team data.
* 🔗 **Reliable Persistence**: MySQL integration with optimized JPA queries and custom entity management for maximum stability.
* 📖 **Live Documentation**: Integrated **Swagger/OpenAPI UI** for real-time API testing.
* 🏗️ **Decoupled Logic**: Clean separation between Security, Business Logic, and Background Tasks.

---

## 🛠️ Technologies Used
* **Java 25** (Latest Long-Term Support)
* **Spring Boot 3.4.3**
* **Spring Security & JWT**
* **Spring Data JPA & MySQL**
* **Maven** (Dependency Management)

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
```

**2. Login (`POST /login`)**
```json
{
  "email": "shehab@example.com",
  "password": "password123"
}
```
**Response:** `{ "token": "eyJhbGci..." }`

### ⚽ Player Endpoints (Bearer Token Required)
**Base URL:** `http://localhost:8081/api/v1/players`

* `GET /` - Fetch all players.
* `GET /{id}` - Fetch player by ID.
* `POST /` - Add a new player (**Admin Only**).
* `PUT /{id}` - Update player details.
* `DELETE /{id}` - Remove a player.

---

## 🛠️ Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/shehabismai10/Premier-Zone.git](https://github.com/shehabismai10/Premier-Zone.git)
   cd Premier-Zone
   ```

2. **Database Configuration:**
   Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/premier_zone
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

3. **Build and Run:**
   ```bash
   mvn clean compile
   mvn spring-boot:run
   ```

---

## 📂 Project Structure
```text
Premier-Zone/
├── src/
│   ├── main/
│   │   ├── java/com/pl/premier_zone/
│   │   │   ├── auth/         # Auth Controller & Service Logic
│   │   │   ├── config/       # Security, JWT & Async Config
│   │   │   ├── user/         # User Entity & Repo
│   │   │   ├── player/       # Player Logic
│   │   │   ├── event/        # Custom Registration Events
│   │   │   └── notification/ # Async Event Listeners
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
```

---

## 👨‍💻 Developer Note
This project has been optimized to handle high-frequency requests by offloading non-critical tasks (like welcome notifications) to background threads using **Spring's Task Executor**. This ensures the API remains responsive and fast.
