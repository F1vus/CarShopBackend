package edu.team.carshopbackend.dto;

import edu.team.carshopbackend.entity.enums.AutoState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AutoDTO {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private String color;
    private Long mileage;
    private String autoStatus;
    private String petrolType;
    private int engineCapacity;
    private int power;
    private AutoState state;
}
