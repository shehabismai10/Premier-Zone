# Premier-Zone

## Description

Premier-Zone is a Java Spring Boot project that manages Premier League player statistics.
It allows users to view, add, update, and delete player data, with a secure JWT-based authentication system.

## Badges

 

## Features

* Full CRUD operations for Premier League players.
* JWT authentication for secure access.
* Role-based access control (ADMIN / USER).
* REST API endpoints for player data.
* Integration with MySQL database for persistent storage.

## Installation

To install this project, follow these steps:

```bash
# Clone the repository
git clone https://github.com/shehabismai10/Premier-Zone.git

# Navigate into the project directory
cd Premier-Zone

# Install dependencies (Maven for Spring Boot)
mvn clean install

# Run the application
mvn spring-boot:run
```

## Usage

* Access the API endpoints via `http://localhost:8080`.
* Use Postman or any HTTP client to test.

### Authentication

#### POST /auth/register

Register a new user.

```json
{
  "username": "user1",
  "password": "password123",
  "role": "USER"
}
```

#### POST /auth/login

Login and get JWT token.

```json
{
  "username": "user1",
  "password": "password123"
}
```

Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Player Endpoints

> **Note:** All player endpoints require the `Authorization: Bearer <JWT_TOKEN>` header.

#### GET /players

Fetch all players.

```json
[
  {
    "id": 1,
    "name": "David Raya",
    "position": "GKP",
    "team": "Arsenal",
    "points": 371
  },
  {
    "id": 2,
    "name": "Aaron Ramsdale",
    "position": "GKP",
    "team": "Arsenal",
    "points": 250
  }
]
```

#### GET /players/{id}

Fetch a single player by ID.

```json
{
  "id": 1,
  "name": "David Raya",
  "position": "GKP",
  "team": "Arsenal",
  "points": 371
}
```

#### POST /players

Add a new player.

```json
{
  "name": "New Player",
  "position": "DEF",
  "team": "Liverpool",
  "points": 0
}
```

Response:

```json
{
  "message": "Player added successfully",
  "playerId": 101
}
```

#### PUT /players/{id}

Update a player by ID.

```json
{
  "name": "Updated Player",
  "position": "MID",
  "team": "Manchester United",
  "points": 10
}
```

Response:

```json
{
  "message": "Player updated successfully"
}
```

#### DELETE /players/{id}

Delete a player by ID. Response:

```json
{
  "message": "Player deleted successfully"
}
```

## Project Structure

```
Premier-Zone/
├── src/
│   ├── main/
│   │   ├── java/com/shehab/premierzone/
│   │   │   ├── controller/
│   │   │   │   └── PlayerController.java
│   │   │   ├── entity/
│   │   │   │   └── Player.java
│   │   │   ├── repository/
│   │   │   │   └── PlayerRepository.java
│   │   │   ├── service/
│   │   │   │   └── PlayerService.java
│   │   │   ├── security/
│   │   │   │   ├── JwtService.java
│   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   └── PremierZoneApplication.java
│   │   └── resources/
│   │       └── application.properties
└── pom.xml
```

## Contributing

We welcome contributions! To contribute to this project, please follow these steps:

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.



## Acknowledgments

* Thank you to all contributors!
