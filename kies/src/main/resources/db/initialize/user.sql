-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER lobster_owner
WITH PASSWORD 'lobster';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO lobster_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO lobster_owner;

CREATE USER lobster_appuser
WITH PASSWORD 'lobster';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO lobster_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO lobster_appuser;