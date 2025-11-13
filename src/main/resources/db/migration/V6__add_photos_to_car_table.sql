BEGIN;

DELETE FROM public.cars;
DELETE FROM public.car_producent;
ALTER TABLE public.cars DROP COLUMN IF EXISTS image_url;

CREATE TABLE IF NOT EXISTS photos (
                                          id BIGSERIAL PRIMARY KEY,
                                          car_id BIGINT NOT NULL,
                                          photo_url TEXT NOT NULL,
                                          FOREIGN KEY (car_id) REFERENCES public.cars(id) ON DELETE CASCADE
    );


INSERT INTO car_producent (id, name)
VALUES
    (1,'Volkswagen'),
    (2,'Toyota'),
    (3,'Audi'),
    (4,'BMW'),
    (5,'Mercedes-Benz'),
    (6,'Renault'),
    (7,'Škoda'),
    (8,'Peugeot'),
    (9,'Kia'),
    (10,'Dacia'),
    (11,'Hyundai'),
    (12,'Ford'),
    (13,'Nissan'),
    (14,'Opel'),
    (15,'Tesla'),
    (16,'Porsche'),
    (17,'Citroën');


INSERT INTO public.cars (id,name, mark_id, price, description, color_id, mileage, car_status, petrol_type_id, engine_capacity, power, manufacture_year)
VALUES
    (1,'Toyota Corolla', 2, 15000, 'Reliable compact car, great fuel economy.', 2, 45000, 'POOR', 1, 1600, 120, 2005),
    (2,'BMW 320i', 4, 28000, 'Luxury sedan with sporty handling.', 1, 60000, 'USED', 1, 2000, 184,  2012),
    (3,'Audi A4', 3, 30000, 'Comfortable premium sedan.', 5, 52000, 'NEW', 1, 2000, 190, 2015),
    (4,'Mercedes C200', 5, 35000, 'Elegant design with advanced safety features.', 6, 30000, 'USED', 2, 1800, 160, 2021),
    (5,'Volkswagen Golf', 1, 17000, 'Compact hatchback, perfect for city driving.', 3, 70000, 'POOR', 1, 1600, 110,  1994),
    (6,'Ford Focus', 12, 16000, 'Popular hatchback with efficient engine.', 4, 50000, 'USED', 1, 1500, 100,  2018),
    (7,'Toyota Prius', 2, 22000, 'Hybrid car with low fuel consumption.', 2, 40000, 'USED', 3, 1800, 140,2024),
    (8,'Porsce 911 Carrera  GTS 3', 16, 175900, 'Comfortable premium sport car', 5, 10000, 'USED', 1, 2500, 478,  2020),
    (9,'Tesla Model 3', 15, 45000, 'Electric car with autopilot and long range.', 1, 15000, 'USED', 4, NULL, 283,  2018);


INSERT INTO public.photos (car_id, photo_url)
VALUES
    (1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Toyota_Corolla_Hybrid_%28E210%29_IMG_4338.jpg/1920px-Toyota_Corolla_Hybrid_%28E210%29_IMG_4338.jpg'),
    (2, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/BMW_G20_%282022%29_IMG_7316_%282%29.jpg/1024px-BMW_G20_%282022%29_IMG_7316_%282%29.jpg'),
    (3, 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Audi_A4_B9_sedans_%28FL%29_1X7A2441.jpg/1024px-Audi_A4_B9_sedans_%28FL%29_1X7A2441.jpg'),
    (4, 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Mercedes-Benz_W206_IMG_6380.jpg/330px-Mercedes-Benz_W206_IMG_6380.jpg'),
    (5, 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/VW_Golf_I_Facelift_front_20081209.jpg/1024px-VW_Golf_I_Facelift_front_20081209.jpg'),
    (6, 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/2018_Ford_Focus_ST-Line_X_1.0.jpg/1024px-2018_Ford_Focus_ST-Line_X_1.0.jpg'),
    (7, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/2024_Toyota_Prius_Excel_PHEV_-_1987cc_2.0_%28225PS%29_Plug-in_Hybrid_-_Silver_Metallic_-_10-2024%2C_Front_Quarter.jpg/1024px-2024_Toyota_Prius_Excel_PHEV_-_1987cc_2.0_%28225PS%29_Plug-in_Hybrid_-_Silver_Metallic_-_10-2024%2C_Front_Quarter.jpg'),
    (8, 'https://images-porsche.imgix.net/-/media/E969499404154DB79BAD58EF5CC8CFAB_82BBE0A2462E47C4B1DB34EA0B23B853_CZ25W12IX0010-911-carrera-gts-side?w=2560&h=697&q=85&crop=faces%2Centropy%2Cedges&auto=format'),
    (9, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/2019_Tesla_Model_3_Performance_AWD_Front.jpg/330px-2019_Tesla_Model_3_Performance_AWD_Front.jpg');

COMMIT;
