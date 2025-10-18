CREATE TABLE autos (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       mark_id BIGINT REFERENCES auto_producent(id),
                       price BIGINT,
                       description TEXT,
                       color_id BIGINT REFERENCES color(id),
                       mileage BIGINT,
                       auto_status VARCHAR(255),
                       petrol_type_id BIGINT REFERENCES petrol(id),
                       engine_capacity INTEGER,
                       power INTEGER,
                       state VARCHAR(50)
);
