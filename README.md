Markdown
# 🏟️ Premier-Zone

## 📖 Description
Premier-Zone is a robust Java Spring Boot application designed to manage and analyze Premier League player statistics. The system features a secure **JWT-based authentication** layer, allowing for personalized user experiences and protected data management.

## 🚀 Features
* **Secure Auth:** Full JWT Authentication (Register/Login).
* **Premier League Data:** Comprehensive player stats (Goals, Assists, Teams).
* **Role-Based Access:** Managed access for `USER` and `ADMIN` roles.
* **Database Integration:** Persistent storage using MySQL.
* **RESTful API:** Clean and versioned endpoints (`/api/v1`).

## 🛠️ Installation & Setup

1. **Clone the repository**
   ```bash
   git clone [https://github.com/shehabismai10/Premier-Zone.git](https://github.com/shehabismai10/Premier-Zone.git)
   cd Premier-Zone
Database Configuration
Update src/main/resources/application.properties with your MySQL credentials:

Properties
spring.datasource.url=jdbc:mysql://localhost:3306/premier_league
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
Build and Run

Bash
mvn clean install
mvn spring-boot:run
🔌 API Documentation
🔐 Authentication Endpoints
Base URL: http://localhost:8081/api/v1/auth

1. Register a New User
POST /register

Body:

JSON
{
  "username": "shehab",
  "email": "shehab@example.com",
  "password": "password123"
}
2. Login
POST /login

Body:

JSON
{
  "email": "shehab@example.com",
  "password": "password123"
}
Response:

JSON
{
  "token": "eyJhbGciOiJIUzM4NCJ9..."
}
⚽ Player Endpoints
Base URL: http://localhost:8081/api/v1/players

Note: These endpoints require the Authorization: Bearer <JWT_TOKEN> header.

GET / - Fetch all players.

GET /{id} - Fetch player by ID.

POST / - Add a new player (Admin Only).

PUT /{id} - Update player details.

DELETE /{id} - Remove a player.

📂 Project Structure
Premier-Zone/
├── src/
│   ├── main/
│   │   ├── java/com/pl/premier_zone/
│   │   │   ├── auth/          # Auth Controller & Service
│   │   │   ├── config/        # Security & JWT Config
│   │   │   ├── user/          # User Entity & Repository
│   │   │   └── player/        # Player Logic (Controller, Service, Repo)
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
🛡️ Technologies Used
Java 21

Spring Boot 3.x

Spring Security & JWT

Spring Data JPA

MySQL

Lombok