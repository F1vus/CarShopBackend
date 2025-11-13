-- New
--V7
CREATE TABLE profiles (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);


ALTER TABLE cars
    ADD COLUMN profiles INT;

ALTER TABLE cars
    ADD CONSTRAINT fk_profiles
        FOREIGN KEY (profiles)
            REFERENCES profiles(id);
