--liquibase formatted sql

--changeset cavemanfrak:01 contextFilter:ddl
CREATE TABLE IF NOT EXISTS continent (
    id INT PRIMARY KEY NOT NULL COMMENT 'Continent ID',
    code CHAR(2) UNIQUE NOT NULL COMMENT 'Continent Code',
    name VARCHAR(100) UNIQUE NOT NULL COMMENT 'Continent Name'
);