CREATE TABLE autos (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       mark_id BIGINT,
                       price BIGINT,
                       description TEXT,
                       color_id BIGINT,
                       mileage BIGINT,
                       auto_status VARCHAR(255),
                       petrol_type_id BIGINT,
                       engine_capacity INTEGER,
                       power INTEGER,
                       state VARCHAR(50),

                       CONSTRAINT fk_mark FOREIGN KEY (mark_id) REFERENCES auto_producent(id),
                       CONSTRAINT fk_color FOREIGN KEY (color_id) REFERENCES color(id),
                       CONSTRAINT fk_petrol FOREIGN KEY (petrol_type_id) REFERENCES petrol(id)
);
