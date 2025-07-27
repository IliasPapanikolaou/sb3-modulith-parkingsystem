# SB3 Modulith Parking System

A modular parking system built with Spring Boot 3, Spring Modulith, and MySQL. The application demonstrates a clean, modular architecture for handling parking entry, slot allocation, billing, and notifications.

## Features

- Modular architecture using Spring Modulith
- MySQL database integration (via Docker)
- RESTful API for vehicle entry and exit
- Event-driven communication between modules
- Example notification and billing logic

## Getting Started

### Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose
- (Optional) IntelliJ IDEA Ultimate for enhanced Docker support

### Setup

1. **Start MySQL with Docker Compose:**

   ```sh
   docker-compose up -d
   ```

2. **Build and Run the Application:**

   ```sh
   ./mvnw spring-boot:run
   ```

   The app will start on [http://localhost:8080](http://localhost:8080).

### Configuration

Database and other settings are in `src/main/resources/application.properties`.

## REST API

### 1. Vehicle Entry

**Endpoint:**  
`POST /api/parking/entry`

**Request Body (form param):**
- `vehicleNumber=ABC123`

**Example cURL:**
```sh
curl -X POST "http://localhost:8080/api/parking/entry" \
  -d "vehicleNumber=ABC123"
```

### 2. Vehicle Exit

**Endpoint:**  
`POST /api/parking/exit`

**Request Body (form param):**
- `vehicleNumber=ABC123`

**Example cURL:**
```sh
curl -X POST "http://localhost:8080/api/parking/exit" \
  -d "vehicleNumber=ABC123"
```

## Modules

- **Entry:** Handles vehicle entry and event publishing.
- **Allocation:** Allocates parking slots.
- **Billing:** Calculates and records billing.
- **Notification:** Sends notifications to users.
- **Event:** Defines domain events.

## Development

- Modular code is under `src/main/java/com/ipap/sb3modulithparkingsystem/`
- Events are published and handled using Spring Modulith's eventing system.

## Docker Compose

The included `docker-compose.yml` spins up a MySQL database for local development.

```yaml
services:
  db:
    image: mysql:latest
    container_name: mysql_db
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=root
      - MYSQL_DATABASE=testdb
```

## License

MIT
