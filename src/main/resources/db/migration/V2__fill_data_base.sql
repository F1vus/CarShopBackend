-- V2__fill_data_base.sql
BEGIN;

-- ===== INSERT PRODUCERS =====
INSERT INTO car_producent (name)
VALUES
    ('Toyota'),
    ('BMW'),
    ('Audi'),
    ('Mercedes-Benz'),
    ('Volkswagen'),
    ('Ford');

-- ===== INSERT COLORS =====
INSERT INTO colors (name)
VALUES
    ('Black'),
    ('White'),
    ('Red'),
    ('Blue'),
    ('Silver'),
    ('Gray');

-- ===== INSERT PETROL TYPES =====
INSERT INTO petrols (name)
VALUES
    ('Petrol'),
    ('Diesel'),
    ('Hybrid'),
    ('Electric');

-- ===== INSERT CARS =====
INSERT INTO cars (name, mark_id, price, description, color_id, mileage, car_status, petrol_type_id, engine_capacity, power, image_url, year)
VALUES
    ('Toyota Corolla', 1, 15000, 'Reliable compact car, great fuel economy.', 2, 45000, 'POOR', 1, 1600, 120, 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Toyota_Corolla_Hybrid_%28E210%29_IMG_4338.jpg/1920px-Toyota_Corolla_Hybrid_%28E210%29_IMG_4338.jpg', 2005),
    ('BMW 320i', 2, 28000, 'Luxury sedan with sporty handling.', 1, 60000, 'USED', 1, 2000, 184, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/BMW_G20_%282022%29_IMG_7316_%282%29.jpg/1024px-BMW_G20_%282022%29_IMG_7316_%282%29.jpg', 2012),
    ('Audi A4', 3, 30000, 'Comfortable premium sedan.', 5, 52000, 'NEW', 1, 2000, 190, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Audi_A4_B9_sedans_%28FL%29_1X7A2441.jpg/1024px-Audi_A4_B9_sedans_%28FL%29_1X7A2441.jpg', 2015),
    ('Mercedes C200', 4, 35000, 'Elegant design with advanced safety features.', 6, 30000, 'USED', 2, 1800, 160, 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Mercedes-Benz_W206_IMG_6380.jpg/330px-Mercedes-Benz_W206_IMG_6380.jpg', 2021),
    ('Volkswagen Golf', 5, 17000, 'Compact hatchback, perfect for city driving.', 3, 70000, 'POOR', 1, 1600, 110, 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/VW_Golf_I_Facelift_front_20081209.jpg/1024px-VW_Golf_I_Facelift_front_20081209.jpg', 1994),
    ('Ford Focus', 6, 16000, 'Popular hatchback with efficient engine.', 4, 50000, 'USED', 1, 1500, 100, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/2018_Ford_Focus_ST-Line_X_1.0.jpg/1024px-2018_Ford_Focus_ST-Line_X_1.0.jpg', 2018),
    ('Toyota Prius', 1, 22000, 'Hybrid car with low fuel consumption.', 2, 40000, 'USED', 3, 1800, 140, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/2024_Toyota_Prius_Excel_PHEV_-_1987cc_2.0_%28225PS%29_Plug-in_Hybrid_-_Silver_Metallic_-_10-2024%2C_Front_Quarter.jpg/1024px-2024_Toyota_Prius_Excel_PHEV_-_1987cc_2.0_%28225PS%29_Plug-in_Hybrid_-_Silver_Metallic_-_10-2024%2C_Front_Quarter.jpg', 2024),
    ('Tesla Model 3', 1, 45000, 'Electric car with autopilot and long range.', 1, 15000, 'USED', 4, NULL, 283, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/2019_Tesla_Model_3_Performance_AWD_Front.jpg/330px-2019_Tesla_Model_3_Performance_AWD_Front.jpg', 2018);

COMMIT;
