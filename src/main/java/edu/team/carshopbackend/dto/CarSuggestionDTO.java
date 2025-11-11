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
    private List<String> photosUrl;

    public CarSuggestionDTO(Long id, String name, Long price, List<String> photos) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photosUrl = photos;  
    }
}
