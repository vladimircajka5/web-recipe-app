# Web Recipe App

Web application for managing recipes, ingredients, and shopping lists.

# Development Environment Setup

This guide explains how to configure and run all three components of the Web Recipe App: the Angular frontend, Spring Boot backend, and PostgreSQL database.

## 1. Prerequisites and Versions

Install the following software before running the project:

| Software | Required version |
|---|---|
| Node.js | 26.13.0 |
| npm | 11.20.0 |
| Java JDK | 21 |
| PostgreSQL | 18 |
| Maven | 3.10.1 (provided through Maven Wrapper) |

No separate Maven installation is required.

## 2. PostgreSQL Database Setup

### 2.1. Start PostgreSQL

Make sure your local PostgreSQL server is running.

The default PostgreSQL port is `5432`.

### 2.2. Create the application user and database

1. Connect to your local PostgreSQL instance as the PostgreSQL administrator (`postgres`):

   ```bash
   psql -U postgres -h localhost -p 5432
   ```

2. Create a dedicated application user. Replace the placeholder with a password of your choice:

   ```sql
   CREATE USER recipes_user WITH ENCRYPTED PASSWORD 'your_password_here';
   ```

3. Create the application database and assign ownership to the application user:

   ```sql
   CREATE DATABASE recipes_db OWNER recipes_user;
   ```

**Important:** Use your own local password. Never commit real passwords or other credentials to the repository.

### 2.3. Verify the database connection

Exit the administrator session:

```sql
\q
```

Connect using the application user:

```bash
psql -U recipes_user -h localhost -p 5432 -d recipes_db
```

Run the following query:

```sql
SELECT 1;
```

The expected result is one row containing the value `1`.

Exit the session using `\q`.

## 3. Frontend Setup (Angular)

The frontend is located in the `frontend/` directory.

1. Navigate to the frontend directory:

   ```bash
   cd frontend
   ```

2. Install dependencies:

   ```bash
   npm ci
   ```

3. Start the development server:

   ```bash
   npm start
   ```

4. Open the application in your browser:

   http://localhost:4200/

To generate a production build:

```bash
npm run build
```

The build output is generated in `frontend/dist/frontend/`.

## 4. Backend Setup (Spring Boot)

The backend is located in `backend/recipes/`.

Make sure JDK 21 is installed and configured correctly.

1. Navigate to the backend directory:

   ```bash
   cd backend/recipes
   ```

2. Run the tests:

   **Windows (PowerShell):**

   ```powershell
   .\mvnw.cmd clean test
   ```

   **Linux/macOS:**

   ```bash
   ./mvnw clean test
   ```

3. Start the Spring Boot application:

   **Windows (PowerShell):**

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   **Linux/macOS:**

   ```bash
   ./mvnw spring-boot:run
   ```

The backend starts on:

http://localhost:8080/

## 5. Local Development Ports

| Component | Local URL / Port |
|---|---|
| Angular frontend | http://localhost:4200 |
| Spring Boot backend | http://localhost:8080 |
| PostgreSQL database | localhost:5432 |

## 6. Development Environment Verification

After completing the setup, verify that:

- PostgreSQL server is running.
- Database `recipes_db` exists.
- Application user `recipes_user` can connect to the database.
- `SELECT 1;` executes successfully using the application user.
- Backend tests pass successfully.
- Spring Boot backend starts on port `8080`.
- Angular frontend starts on port `4200`.
- Angular production build completes successfully.