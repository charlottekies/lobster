--liquibase formatted sql

--changeset charlotte.kies:2

ALTER TABLE users
   INSERT name varchar(256),
   INSERT first_name varchar(50),
   insert last_name varchar(50),
   insert image varchar(500);

--rollback DROP COLUMN name, first_name, last_name, image;
