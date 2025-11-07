# 🌍 Travel Log Application

A full-stack web application for tracking and reviewing your travel experiences. Built with Spring Boot REST API backend and React SPA frontend.

<img width="1483" height="815" alt="Zrzut ekranu 2025-11-7 o 12 35 50" src="https://github.com/user-attachments/assets/a806e40f-c982-49ba-bfd7-f55fa9aa8547" />

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)

## ✨ Features

- 📝 Create and manage travel entries
- 🗓️ Track travel dates, destinations, and costs
- ⭐ Rate your trips (1-10 scale)
- 📖 Write and update travel reviews
- 📱 Responsive single-page application
- 🔍 View detailed information for each trip
- ✅ Form validation (client and server-side)

## 🛠️ Tech Stack

### Backend
- **Java 11+**
- **Spring Boot 2.x**
  - Spring Web (REST API)
  - Spring Validation
- **MapStruct** - Object mapping
- **Maven** - Dependency management

### Frontend
- **React 18+**
- **React Router v6** - Client-side routing
- **Vite** - Build tool and dev server
- **Axios/Fetch** - HTTP client
- **CSS3** - Styling

### Data Layer
- In-memory repository (development)
- Ready for database integration

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────┐
│                     React SPA                           │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐ │
│  │  List View   │  │ Create Form  │  │ Detail View  │ │
│  └──────────────┘  └──────────────┘  └──────────────┘ │
└─────────────────────────────────────────────────────────┘
                           │
                    HTTP JSON API
                           │
┌─────────────────────────────────────────────────────────┐
│              Spring Boot REST API                       │
│  ┌──────────────────────────────────────────────────┐  │
│  │         TravellingApiController                  │  │
│  │  GET    /api/travellings                         │  │
│  │  GET    /api/travellings/{id}                    │  │
│  │  POST   /api/travellings                         │  │
│  │  PUT    /api/travellings/{id}                    │  │
│  │  DELETE /api/travellings/{id}                    │  │
│  └──────────────────────────────────────────────────┘  │
│                           │                             │
│  ┌─────────────┐   ┌──────────────┐   ┌────────────┐  │
│  │   Mapper    │   │     DTOs     │   │ Exception  │  │
│  │ (MapStruct) │   │  (Request/   │   │  Handling  │  │
│  │             │   │   Response)  │   │            │  │
│  └─────────────┘   └──────────────┘   └────────────┘  │
│                           │                             │
│  ┌──────────────────────────────────────────────────┐  │
│  │          Repository Layer                        │  │
│  │  (In-Memory / Database Integration)              │  │
│  └──────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
```

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- Node.js 16+ and npm
- Git

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/PiotrJerzy13/java-mvc-travel-site.git
   cd travel-log
   ```

2. **Build the backend**
   ```bash
   mvn clean install
   ```

3. **Run the Spring Boot application**
   ```bash
   mvn spring-boot:run
   ```

   The API will be available at `http://localhost:8080`

### Frontend Setup

1. **Navigate to frontend directory**
   ```bash
   cd traveling-frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Start development server**
   ```bash
   npm run dev
   ```

   The application will be available at `http://localhost:5173`

### Quick Start (Development)

```bash
# Terminal 1 - Backend
mvn spring-boot:run

# Terminal 2 - Frontend
cd traveling-frontend && npm run dev
```

Visit `http://localhost:5173` in your browser.

## 📡 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Endpoints

#### Get All Travellings
```http
GET /api/travellings
```

**Response:**
```json
[
  {
    "id": 1,
    "destination": "Paris",
    "startDate": "2024-06-01",
    "endDate": "2024-06-10",
    "cost": 2500.00,
    "rating": 9
  }
]
```

#### Get Travelling Details
```http
GET /api/travellings/{id}
```

**Response:**
```json
{
  "id": 1,
  "destination": "Paris",
  "startDate": "2024-06-01",
  "endDate": "2024-06-10",
  "cost": 2500.00,
  "rating": 9,
  "review": "Amazing trip! The Eiffel Tower was breathtaking..."
}
```

#### Create Travelling
```http
POST /api/travellings
Content-Type: application/json
```

**Request Body:**
```json
{
  "destination": "Tokyo",
  "startDate": "2024-09-15",
  "endDate": "2024-09-25",
  "cost": 3500.00,
  "rating": 10,
  "review": "Incredible experience!"
}
```

**Response:** `201 Created` with created object

#### Update Travelling
```http
PUT /api/travellings/{id}
Content-Type: application/json
```

**Request Body:**
```json
{
  "rating": 10,
  "review": "Updated review after reflection..."
}
```

**Response:** `200 OK` with updated object

#### Delete Travelling
```http
DELETE /api/travellings/{id}
```

**Response:** `204 No Content`

### Error Responses

All errors follow a consistent format:

```json
{
  "timestamp": "2025-11-06T14:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Travelling with id 999 not found",
  "path": "/api/travellings/999"
}
```

**Status Codes:**
- `200 OK` - Successful GET/PUT
- `201 Created` - Successful POST
- `204 No Content` - Successful DELETE
- `400 Bad Request` - Validation error
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

## 📁 Project Structure

```
travel-log/
├── src/
│   ├── main/
│   │   ├── java/com/piotrwojnarowski/
│   │   │   ├── api/
│   │   │   │   ├── controller/
│   │   │   │   │   └── TravellingApiController.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── TravellingListDTO.java
│   │   │   │   │   ├── TravellingDetailDTO.java
│   │   │   │   │   ├── CreateTravellingRequest.java
│   │   │   │   │   └── UpdateTravellingRequest.java
│   │   │   │   ├── mapper/
│   │   │   │   │   └── TravellingMapper.java
│   │   │   │   └── exception/
│   │   │   │       ├── GlobalExceptionHandler.java
│   │   │   │       └── TravellingNotFoundException.java
│   │   │   ├── domain/
│   │   │   │   └── Travelling.java
│   │   │   ├── data/
│   │   │   │   ├── TravellingRepository.java
│   │   │   │   └── InMemoryTravellingRepository.java
│   │   │   └── Application.java
│   │   └── resources/
│   │       ├── application.properties
│   └── test/
│       └── java/
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   │   ├── TravellingList.jsx
│   │   │   ├── TravellingDetails.jsx
│   │   │   └── CreateTravelling.jsx
│   │   ├── services/
│   │   │   └── travellingApi.js
│   │   ├── App.jsx
│   │   └── main.jsx
│   ├── public/
│   ├── package.json
│   └── vite.config.js
├── pom.xml
└── README.md
```

## 💻 Development

### Building for Production

**Backend:**
```bash
mvn clean package
```

This creates `target/travel-log-0.0.1-SNAPSHOT.jar`

**Frontend:**
```bash
cd frontend
npm run build
```

This creates optimized files in `traveling-frontend/dist/`

### Production Deployment (Single JAR)

1. **Build frontend**
   ```bash
   cd frontend
   npm run build
   ```
2. **Package Spring Boot application**
   ```bash
   mvn clean package
   ```

3. **Run the JAR**
   ```bash
   java -jar target/travel-log-0.0.1-SNAPSHOT.jar
   ```

   Application available at `http://localhost:8080`

### CORS Configuration

For development, CORS is configured to allow:
- Origins: `http://localhost:5173`, `http://localhost:3000`
- Methods: GET, POST, PUT, DELETE
- Headers: Content-Type, Authorization

Update `CorsConfig.java` for production domains.

### Environment Variables

**Backend:**
```properties
# application.properties
server.port=8080
spring.application.name=travel-log
```

**Frontend:**
```bash
# .env
VITE_API_URL=http://localhost:8080
```

## 🔄 Migration Notes

This project is migrating from Spring MVC + Thymeleaf to REST API + React SPA.
