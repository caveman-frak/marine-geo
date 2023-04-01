--liquibase formatted sql

--changeset cavemanfrak:01 contextFilter:ddl
CREATE TABLE IF NOT EXISTS country (
    code CHAR(2) PRIMARY KEY NOT NULL COMMENT 'Country 2 character Code',
    code3 CHAR(3) UNIQUE NOT NULL COMMENT 'Country 3 character Code',
    name VARCHAR(100) UNIQUE NOT NULL COMMENT 'Country Name',
    subcontinent INT NOT NULL COMMENT 'Primary Sub-Continent',
    numeric_code DECIMAL(3) UNIQUE NOT NULL COMMENT 'Numeric ISO Code',
    native_name VARCHAR(100) NULL COMMENT 'Name in Native Language',
    flag CHAR(4) NULL COMMENT 'Emoji Flag',
    flag_unicode CHAR(15) NULL COMMENT 'Unicode Flag',
    CONSTRAINT  fk_subcontinent_country
        FOREIGN KEY (subcontinent) REFERENCES subcontinent (id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);