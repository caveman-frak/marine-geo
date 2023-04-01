--liquibase formatted sql

--changeset cavemanfrak:01 contextFilter:ddl
CREATE TABLE IF NOT EXISTS subcontinent (
    id INT PRIMARY KEY NOT NULL COMMENT 'Sub-Continent ID',
    name VARCHAR(100) UNIQUE NOT NULL COMMENT 'Sub-Continent Name',
    continent INT NOT NULL COMMENT 'Continent ID',
    CONSTRAINT  fk_continent_subcontinent
            FOREIGN KEY (continent) REFERENCES continent (id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
);