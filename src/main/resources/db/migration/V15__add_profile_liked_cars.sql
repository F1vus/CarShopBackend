CREATE TABLE profile_liked_cars (
                                    profile_id BIGINT NOT NULL,
                                    car_id BIGINT NOT NULL,
                                    PRIMARY KEY (profile_id, car_id),
                                    FOREIGN KEY (profile_id) REFERENCES users_profiles(id),
                                    FOREIGN KEY (car_id) REFERENCES cars(id)
);
