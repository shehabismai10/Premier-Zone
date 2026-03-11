# Premier-Zone

## Description
Premier-Zone is a Java Spring Boot project that manages Premier League player statistics.  
It allows users to view, add, update, and delete player data, with a secure JWT-based authentication system.

## Badges
![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Coverage Status](https://img.shields.io/badge/coverage-95%25-brightgreen)

## Features
- Full CRUD operations for Premier League players.
- JWT authentication for secure access.
- Role-based access control (ADMIN / USER).
- REST API endpoints for player data.
- Integration with MySQL database for persistent storage.

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
mvn spring-boot:run.



## Usage
- Access the API endpoints via `http://localhost:8080`.
- Use Postman or any HTTP client to test:

### Endpoints
#### GET /players
Fetch all players.
Example response:
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
