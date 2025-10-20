package edu.team.carshopbackend.dto;

import edu.team.carshopbackend.entity.enums.CarState;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class CarDTO {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private String color;
    private Long mileage;
    private CarState state;
    private String petrolType;
    private Integer engineCapacity;
    private Integer power;
    private Integer year;
    private String imageUrl;
}
