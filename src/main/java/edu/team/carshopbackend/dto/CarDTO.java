package edu.team.carshopbackend.dto;

import edu.team.carshopbackend.entity.enums.CarState;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CarDTO {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private ColorDTO color;
    private Long mileage;
    private CarState carState;
    private PetrolDTO petrolType;
    private Integer engineCapacity;
    private Integer power;
    private Integer year;
    private String photosUrl;
    private CarProducentDTO producent;
}


