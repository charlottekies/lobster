--liquibase formatted sql

--changeset charlotte.kies:6


ALTER TABLE users
   ADD COLUMN provider varchar(200);


--rollback ALTER TABLE users DROP COLUMN provider;