Here is the complete, fixed `README.md` file. I also added the **API Documentation (Swagger)** section at the bottom, which we set up just a few minutes ago!

You can copy this entire block and replace the contents of your `README.md`:

```markdown
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

```

### 2. Run the Application

Set your environment variables and start the server:

```bash
export GOOGLE_CLIENT_ID="your_id"
export GOOGLE_CLIENT_SECRET="your_secret"
./gradlew bootRun

```

### 3. Testing OAuth2

Visit the following URL in your browser to trigger the Google login flow:
`http://localhost:8080/oauth2/authorization/google`

---

## API Endpoints

### Authentication

* `POST /api/auth/register` - Register a new user with email and password.
* `POST /api/auth/login` - Authenticate and receive a JWT.
* `GET /api/auth/oauth-success` - Callback endpoint that returns the JWT after a Google login.

### Leads (Next Phase)

* `GET /api/leads` - List leads assigned to the current user.
* `POST /api/leads` - Create and assign a new lead.

---

## API Documentation

Interactive API documentation is available via Swagger UI when the application is running.
You can access it at:

`http://localhost:8080/swagger-ui/index.html`

---

## License

Distributed under the MIT License.

```

Would you like me to generate the Java code for the `Lead` Entity and its database migration now?

```