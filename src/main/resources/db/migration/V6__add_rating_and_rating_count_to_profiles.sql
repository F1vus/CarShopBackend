-- V6__add_rating_columns_to_users_profiles.sql
BEGIN;

ALTER TABLE users_profiles
    ADD COLUMN rating DOUBLE PRECISION DEFAULT 0.0;

ALTER TABLE users_profiles
    ADD COLUMN rating_count INT DEFAULT 0;

COMMIT;
