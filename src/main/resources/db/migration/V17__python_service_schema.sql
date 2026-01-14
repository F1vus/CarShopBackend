CREATE SCHEMA IF NOT EXISTS photoservice;

CREATE TABLE IF NOT EXISTS photoservice.photos (
                                     id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                     car_id BIGINT NOT NULL,
                                     uploaded_by_user_id BIGINT,
                                     filename TEXT,
                                     content_type TEXT,
                                     format TEXT,
                                     hash TEXT,
                                     size_bytes INTEGER,
                                     created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Photo variants table
CREATE TABLE IF NOT EXISTS photoservice.photo_variants (
                                             id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                             photo_id BIGINT NOT NULL,
                                             width INTEGER NOT NULL,
                                             height INTEGER,
                                             data BYTEA,
                                             size_bytes INTEGER,
                                             content_type TEXT,
                                             created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    -- Unique constraint: each photo can have only one variant per width
                                             UNIQUE (photo_id, width),

    -- Foreign key constraint with cascade delete
                                             CONSTRAINT photo_variants_photo_id_fkey
                                                 FOREIGN KEY (photo_id)
                                                     REFERENCES photoservice.photos(id)
                                                     ON DELETE CASCADE
);

-- Create index on photos.id (already has primary key index, but useful for certain queries)
CREATE INDEX IF NOT EXISTS ix_photoservice_photos_id ON photoservice.photos (id);