BEGIN;

CREATE TABLE IF NOT EXISTS car_photos (
                                          id BIGSERIAL PRIMARY KEY,
                                          car_id BIGINT NOT NULL,
                                          photo_url TEXT NOT NULL,
                                          FOREIGN KEY (car_id) REFERENCES cars(id) ON DELETE CASCADE
    );

ALTER TABLE cars DROP COLUMN IF EXISTS photos;

COMMIT;
