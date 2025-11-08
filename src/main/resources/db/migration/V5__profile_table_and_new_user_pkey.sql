-- V5__profile_table_and_new_user_pkey.sql
BEGIN;

DROP TABLE IF EXISTS users_profiles CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
                       user_id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(32) NOT NULL UNIQUE,
                       password TEXT NOT NULL
);

CREATE TABLE users_profiles (
                                profile_id BIGSERIAL PRIMARY KEY,
                                user_id BIGINT NOT NULL UNIQUE,
                                CONSTRAINT fk_profiles_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

COMMIT;
