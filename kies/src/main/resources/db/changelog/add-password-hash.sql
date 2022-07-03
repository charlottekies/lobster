--liquibase formatted sql

--changeset charlotte.kies:5


ALTER TABLE users
   ADD COLUMN password_hash varchar(200);


--rollback ALTER TABLE users DROP COLUMN password_hash;