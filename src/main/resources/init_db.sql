CREATE DATABASE IF NOT EXISTS versionupdater CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE versionupdater;

CREATE USER IF NOT EXISTS 'depverup'@'%' IDENTIFIED BY 'depveruppw';
GRANT ALL PRIVILEGES ON versionupdater.* TO 'depverup'@'%';