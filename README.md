# Closure CRM API

**Closure CRM API** is a robust, production-ready backend built with Spring Boot, designed to streamline lead management and sales operations. The system features a hybrid authentication model supporting both traditional credentials and Google OAuth2.

## Features

* **Dual Authentication System**: Support for traditional Email/Password login and Google OAuth2.
* **JWT Security**: Stateless authentication using signed JSON Web Tokens (JWT).
* **Role-Based Access Control (RBAC)**: Fine-grained permissions with roles like `ROLE_SALES_REP`.
* **Automated User Provisioning**: New OAuth2 users are automatically created in the database upon their first successful login.
* **Database Migrations**: Managed schema versioning and version control using Flyway.
* **Standardized API Responses**: Every endpoint returns a consistent JSON structure for easier frontend integration.

## Tech Stack

* **Java 17** & **Spring Boot 3.5.x**
* **Spring Security** (JWT & OAuth2 Client)
* **PostgreSQL** (Database)
* **Flyway** (Database Migrations)
* **Lombok** (Boilerplate code reduction)
* **Docker** (Containerized database management)

## Configuration & Environment

The project follows security best practices by externalizing all sensitive credentials and secrets.

### Required Environment Variables
To run the application locally, you must set the following variables in your IDE or terminal:

| Variable | Description |
| :--- | :--- |
| `GOOGLE_CLIENT_ID` | Your Google Cloud Console Client ID |
| `GOOGLE_CLIENT_SECRET` | Your Google Cloud Console Client Secret |
| `JWT_SECRET_KEY` | 256-bit key for signing JWTs (Optional, defaults provided) |
| `DB_PASSWORD` | Password for your local PostgreSQL instance |

---

## Getting Started

### 1. Database Setup
Ensure your PostgreSQL container is running:
```bash
docker compose up -d