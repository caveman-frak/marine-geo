--liquibase formatted sql

--changeset cavemanfrak:01 contextFilter:ddl
CREATE TABLE IF NOT EXISTS currency (
    code CHAR(3) NOT NULL PRIMARY KEY COMMENT 'Currency Code',
    name VARCHAR(100) NOT NULL COMMENT 'Currency Name',
    numeric_code DECIMAL(3) NULL COMMENT 'Numeric ISO Code',
    minor DECIMAL(1) NULL COMMENT 'Minor Unit',
    symbol VARCHAR(6) NULL COMMENT 'Optional Currency Symbol'
);