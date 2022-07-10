--liquibase formatted sql

--changeset charlotte.kies:8


CREATE SEQUENCE seq_vote_type_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

--rollback DROP SEQUENCE seq_vote_type_id;