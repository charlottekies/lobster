--liquibase formatted sql

--changeset charlotte.kies:4

ALTER TABLE users
   DROP COLUMN password_hash;

--rollback ALTER TABLE USERS ADD COLUMN password_hash varchar(200) NOT NULL;