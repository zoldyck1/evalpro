




DROP DATABASE IF EXISTS evaluation_db;
CREATE DATABASE evaluation_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE evaluation_db;

CREATE TABLE roles (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE users (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    email         VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(128) NOT NULL,
    salt          VARCHAR(64)  NOT NULL,
    role_id       INT NOT NULL,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE managers (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT NOT NULL UNIQUE,
    full_name  VARCHAR(120) NOT NULL,
    department VARCHAR(80),
    CONSTRAINT fk_manager_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE employees (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id    INT NOT NULL UNIQUE,
    full_name  VARCHAR(120) NOT NULL,
    position   VARCHAR(80),
    department VARCHAR(80),
    hire_date  DATE,
    manager_id INT NULL,
    CONSTRAINT fk_emp_user    FOREIGN KEY (user_id)    REFERENCES users(id)    ON DELETE CASCADE,
    CONSTRAINT fk_emp_manager FOREIGN KEY (manager_id) REFERENCES managers(id) ON DELETE SET NULL
);

CREATE TABLE evaluations (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    employee_id  INT NOT NULL,
    manager_id   INT NOT NULL,
    score        INT CHECK (score BETWEEN 0 AND 100),
    comments     TEXT,
    status       ENUM('DRAFT','VALIDATED') DEFAULT 'DRAFT',
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    validated_at DATETIME NULL,
    CONSTRAINT fk_eval_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    CONSTRAINT fk_eval_manager  FOREIGN KEY (manager_id)  REFERENCES managers(id)
);



INSERT INTO roles (name) VALUES ('EMPLOYEE'), ('MANAGER'), ('RH_ADMIN');






INSERT INTO users (email, password_hash, salt, role_id) VALUES
  ('admin@demo.com',    'c1c55f926c941ad8711ce3ffebcb60350699d689308286bc945d0914ffaca38f', 'demosalt12345678', 3),
  ('manager@demo.com',  'c1c55f926c941ad8711ce3ffebcb60350699d689308286bc945d0914ffaca38f', 'demosalt12345678', 2),
  ('employee@demo.com', 'c1c55f926c941ad8711ce3ffebcb60350699d689308286bc945d0914ffaca38f', 'demosalt12345678', 1);


INSERT INTO managers (user_id, full_name, department)
  VALUES (2, 'Manager Name', 'Engineering');

INSERT INTO employees (user_id, full_name, position, department, hire_date, manager_id)
  VALUES (3, 'Employee Name', 'Software Developer', 'Engineering', '2022-03-15', 1);
