# 🚗 Car Buying Application

A comprehensive Spring Boot application for managing car buying requests and supplier offers with automated inspection company integration.

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [Architecture](#architecture)
- [Configuration](#configuration)
- [Troubleshooting](#troubleshooting)

---

## 🎯 Overview

The Car Buying Application is a modern REST API that connects customers looking to buy cars with multiple suppliers. The system includes:

- **Customer Requests**: Customers create requests specifying their car requirements
- **Supplier Offers**: Multiple suppliers can submit competitive offers

---

## ✨ Features

### Core Functionality
- 🚙 **Customer Request Management**
  - Create, view, update, and list car buying requests
  - Support for filtering and pagination
  - Track number of offers per request
  - Request status management (PENDING, ACTIVE, CLOSED)

- 💼 **Supplier Offer Management**
  - Submit competitive offers on customer requests
  - Automatic inspection company integration
  - One offer per supplier per request (enforced)
  - Real-time inspection scoring

- 🔍 **Inspection Integration**
  - Support for multiple inspection companies:
    - **AUTO_CHECK_CO** - Automated vehicle inspection service
    - **VEHI_VERIFY_INC** - Vehicle verification and inspection
  - Automatic score assignment (0-100)
  - Strategy pattern implementation for extensibility

### Technical Features
- 📊 **Performance Optimized**
  - Database indexes for 100K+ records
  - Pagination support on all list endpoints
  - Efficient JPA queries with lazy loading

- 🔒 **Data Validation**
  - Bean validation on all inputs
  - Business rule validation
  - Multi-layer inspection score validation (0-100)

- 📝 **API Documentation**
  - Interactive Swagger UI
  - Comprehensive endpoint descriptions
  - Request/response examples

- 🗄️ **Database Management**
  - Flyway migrations for version control
  - H2 in-memory database (development)
  - Easy MySQL/PostgreSQL migration (production)

---

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Spring Boot** | 4.0.0 | Application framework |
| **Java** | 17 | Programming language |
| **Spring Data JPA** | 4.0.0 | Data persistence |
| **H2 Database** | 2.x | In-memory database (dev) |
| **Flyway** | 10.x | Database migrations |
| **Lombok** | 1.18.x | Reduce boilerplate code |
| **Springdoc OpenAPI** | 2.6.0 | API documentation (Swagger) 
| **Maven** | 3.6+ | Build tool |

---

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **Git** (optional)

### Installation

1. **Extract/Clone the Project**
```bash
cd car-buying-app
```

2. **Build the Project**
```bash
mvn clean install
```

3. **Run the Application**
```bash
mvn spring-boot:run
```

4. **Verify It's Running**

The application will start on `http://localhost:8080`

You should see:
```
===========================================
Car Buying Application Started Successfully!
Time: 2024-12-17 15:30:45
Swagger UI: http://localhost:8080/swagger-ui/index.html
H2 Console: http://localhost:8080/h2-console
===========================================
```

### Quick Access

| Resource | URL |
|----------|-----|
| **API Base** | http://localhost:8080/api |
| **Swagger UI** | http://localhost:8080/swagger-ui.html |
| **H2 Console** | http://localhost:8080/h2-console

---

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication
Currently, no authentication is required (add Spring Security for production).

### Package Structure

```
com.carbuying
├── adapter/              # Inspection company adapters
│   ├── impl/
│   │   ├── AutoCheckCoAdapter.java
│   │   └── VehiVerifyIncAdapter.java
│   ├── InspectionAdapter.java (interface)
│   └── InspectionAdapterFactory.java
├── config/               # Configuration classes
│   ├── OpenApiConfig.java
│   └── FlywayConfig.java
├── controller/           # REST API controllers
│   ├── CustomerRequestController.java
│   ├── SupplierOfferController.java
│   └── AppInfoController.java
├── dto/                  # Data Transfer Objects
│   ├── CreateRequestDTO.java
│   ├── CreateOfferDTO.java
│   ├── CustomerRequestResponseDTO.java
│   └── SupplierOfferResponseDTO.java
├── entity/               # JPA entities
│   ├── CustomerRequest.java
│   └── SupplierOffer.java
├── enums/                # Enumerations
│   ├── RequestStatus.java
│   ├── OfferStatus.java
│   └── InspectionCompany.java
├── exception/            # Exception handling
│   ├── GlobalExceptionHandler.java
│   ├── BusinessException.java
│   ├── ResourceNotFoundException.java
│   └── ErrorResponse.java
├── repository/           # Data access layer
│   ├── CustomerRequestRepository.java
│   └── SupplierOfferRepository.java
├── service/              # Business logic
│   ├── CustomerRequestService.java
│   └── SupplierOfferService.java
└── CarBuyingApplication.java  # Main application class
```

### H2 Console Access

- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:carbuying`
- **Username**: `sa`
- **Password**: `password`


## 🎉 Quick Start Summary

```bash
# 1. Build
mvn clean install

# 2. Run
mvn spring-boot:run

# 3. Access
open http://localhost:8080/swagger-ui.html

# 4. Test
curl http://localhost:8080/api/info
```

**Happy Coding! 🚗💨**
