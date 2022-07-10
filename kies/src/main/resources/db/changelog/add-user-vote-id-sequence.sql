--liquibase formatted sql

--changeset charlotte.kies:10


CREATE SEQUENCE seq_user_vote_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

--rollback DROP SEQUENCE seq_user_vote_id;