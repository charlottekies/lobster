--liquibase formatted sql

--changeset charlotte.kies:9



CREATE TABLE vote_type (
	vote_type_id int DEFAULT nextval('seq_vote_type_id'::regclass) NOT NULL,
	vote_type varchar(50) NOT NULL UNIQUE,

	CONSTRAINT PK_vote_type PRIMARY KEY (vote_type_id)
);

--rollback DROP TABLE vote_type;