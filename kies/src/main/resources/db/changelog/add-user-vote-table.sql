--liquibase formatted sql

--changeset charlotte.kies:11

CREATE TABLE user_vote (
	user_vote_id int DEFAULT nextval('seq_user_vote_id'::regclass) NOT NULL,
    vote_date date NOT NULL,
    user_id numeric NOT NULL,
    vote numeric(6,2) NOT NULL,

	CONSTRAINT PK_user_vote_id PRIMARY KEY (user_vote_id)
);

--rollback DROP TABLE user_vote;