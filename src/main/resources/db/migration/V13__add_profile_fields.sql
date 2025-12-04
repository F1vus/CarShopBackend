-- V13__add_profile_fields.sql

ALTER TABLE public.users_profiles
    ADD COLUMN profile_phone_number VARCHAR(40),
    ADD COLUMN profile_image TEXT;
