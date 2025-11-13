package edu.team.carshopbackend.dto;

import edu.team.carshopbackend.entity.enums.CarState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<PhotoDTO> photos;
    private CarProducentDTO producent;
}


