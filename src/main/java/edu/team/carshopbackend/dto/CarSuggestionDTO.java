package edu.team.carshopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSuggestionDTO {
    private Long id;
    private String name;
    private Long price;
    private List<String> photos;
}
