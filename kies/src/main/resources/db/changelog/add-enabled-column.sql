--liquibase formatted sql

--changeset charlotte.kies:7


ALTER TABLE users
   ADD COLUMN enabled boolean;


--rollback ALTER TABLE users DROP COLUMN enabled;