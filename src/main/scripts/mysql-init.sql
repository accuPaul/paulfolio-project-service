DROP DATABASE IF EXISTS projectservice;
DROP USER IF EXISTS `project_service`@`%`;
CREATE DATABASE IF NOT EXISTS projectservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `project_service`@`%` IDENTIFIED WITH caching_sha2_password BY `${DB_SERVICE_PASSWORD}`
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `projectservice`.* TO `project_service`@`%`;
FLUSH PRIVILEGES;