package edu.team.carshopbackend.controller;


import edu.team.carshopbackend.dto.AutoDTO;
import edu.team.carshopbackend.entity.Auto;
//import edu.team.carshopbackend.repository.AutoRepository;
import edu.team.carshopbackend.service.AutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class AutoController {


//    private final AutoRepository autoRepository;
    private final AutoService autoService;

    public AutoController(AutoService autoService) {
//        this.autoRepository = autoRepository;
        this.autoService = autoService;
    }

    @GetMapping("/cars")
    public List<AutoDTO> findAllAutos() {
        return autoService.getAllProducts().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @GetMapping("/cars/{id}")
    public AutoDTO findAutoById(@PathVariable Long id) {
        Auto auto = autoService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("No Auto found with id " + id));
        return mapToDTO(auto);
    }


    private AutoDTO  mapToDTO(Auto auto) {
        return new AutoDTO(
                auto.getId(),
                auto.getName(),
                auto.getPrice(),
                auto.getDescription(),
                auto.getColor() != null ? auto.getColor().getName() : null,
                auto.getMileage(),
                auto.getAuto_status(),
                auto.getPetrolType() != null ? auto.getPetrolType().getName() : null,
                auto.getEngine_capacity(),
                auto.getPower(),
                auto.getState()
        );
    }



}
