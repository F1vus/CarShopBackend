package edu.team.carshopbackend.dto;


import edu.team.carshopbackend.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private Long id;
    private String name;
    private List<CarDTO> cars;
}
