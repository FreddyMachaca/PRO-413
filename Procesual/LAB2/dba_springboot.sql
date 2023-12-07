CREATE DATABASE IF NOT EXISTS dba_springboot;
USE dba_springboot;

CREATE TABLE IF NOT EXISTS persona (
    id_persona BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    saldo DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS rol (
    id_rol BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario_rol (
    id_usuario BIGINT NOT NULL,
    id_rol BIGINT NOT NULL,
    PRIMARY KEY (id_usuario, id_rol),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

INSERT INTO rol (nombre) VALUES ('ROLE_USER'), ('ROLE_ADMIN');
INSERT INTO usuario (username, password) VALUES ('admin', '$2a$10$7q3IGexkQI56YxPiY5c/I.R3/qzX/oAjwXnhvvgWKMEAl/ZbXMnJa');

INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (1, 1), (1, 2);

INSERT INTO persona (nombre, apellido, email, telefono, saldo) VALUES
('Juan', 'Perez', 'juanperez@mail.com', '1234567890', 100.50),
('Maria', 'Lopez', 'marialopez@mail.com', '0987654321', 200.75);
