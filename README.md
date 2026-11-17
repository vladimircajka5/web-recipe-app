# web-recipe-app
Web application for managing recipes, ingredients and shopping lists.
# Recipes Project - Development Environment Setup

Welcome to the Recipes project! This guide covers the setup and execution of all three components (Frontend, Backend, and Database) for local development.

---

## 1. Prerequisites and Versions

To ensure a smooth workflow in Sprint 2, make sure you have the following installed:
* **Node.js:** `v26.13.0` (with `npm v11.20.0`)
* **Java Development Kit (JDK):** Recommended LTS version (Java 17 or 21)
* **PostgreSQL:** Local database instance

---

## 2. PostgreSQL Database Setup

1. Connect to your local PostgreSQL instance as a superadministrator (`postgres`).
2. Create an empty database and a dedicated application user:
   ```sql
   CREATE DATABASE recipes_db;
   CREATE USER recipes_user WITH ENCRYPTED PASSWORD 'your_password_here';
   GRANT ALL PRIVILEGES ON DATABASE recipes_db TO recipes_user;