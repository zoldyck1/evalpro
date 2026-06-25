# Application de Gestion des Évaluations des Employés

A Java EE web application for managing employee evaluations.
Built with **Servlets + JSP + JDBC + MySQL** — no Spring, no Hibernate.

## Tech Stack
- Java 11+
- Jakarta Servlet 4 (`javax.servlet`) — targets **Apache Tomcat 9**
- JSP + JSTL
- JDBC + MySQL Connector/J 8
- MySQL 8
- Maven

## 1. Database setup

```bash
mysql -u root -p < sql/schema.sql
```

This creates the `evaluation_db` database, all tables, and seeds 3 demo accounts.

## 2. Configure DB credentials (via environment variables)

The app reads DB connection from these environment variables (or falls back to defaults):

| Variable   | Default value                                                           |
|------------|-------------------------------------------------------------------------|
| `DB_URL`   | `jdbc:mysql://localhost:3306/evaluation_db?useSSL=false&serverTimezone=UTC` |
| `DB_USER`  | `root`                                                                  |
| `DB_PASS`  | *(empty)*                                                               |

Set them before starting Tomcat, for example:
```powershell
$env:DB_URL  = "jdbc:mysql://localhost:3306/evaluation_db?useSSL=false&serverTimezone=UTC"
$env:DB_USER = "root"
$env:DB_PASS = "your_password"
```

## 3. Build

```bash
mvn clean package
```

Produces `target/evaluation-app.war`.

## 4. Deploy

```bash
mvn clean package
```

Copy `target/evaluation-app.war` to your Tomcat `webapps/` directory and restart Tomcat.

Open: <http://localhost:8080/evaluation-app/>

## Demo accounts (password: `demo123`)

| Role      | Email             |
|-----------|-------------------|
| RH Admin  | admin@demo.com    |
| Manager   | manager@demo.com  |
| Employee  | employee@demo.com |

## Structure

```
src/main/java/com/eval/
  model/        POJOs
  dao/          DAO interfaces + JDBC implementations
  util/         DB connection, password hashing
  filter/       Authentication / role guard filter
  controller/   Servlets (auth, employee, manager, admin)
src/main/webapp/
  WEB-INF/views/  JSP views (protected)
  WEB-INF/web.xml
  css/style.css
sql/schema.sql
```

## Notes
- Passwords stored as salted SHA-256 (see `PasswordUtil`).
- All SQL uses `PreparedStatement` (SQL-injection safe).
- Sessions managed via `HttpSession`; `AuthFilter` enforces role-based access.
- For Tomcat 10+ (jakarta namespace) replace `javax.servlet` with `jakarta.servlet` in imports and `pom.xml`.
