package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.mapper.CarMapper;
import edu.team.carshopbackend.service.CarService;
import edu.team.carshopbackend.dto.CarDTO;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Car Controller", description = "Zarządza samochodami w systemie")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    @Operation(summary = "Pobiera listę wszystkich samochodów")
    public List<CarDTO> findAllAutos() {
        return carService.getAllProducts().stream()
                .map(CarMapper::mapToDTO)
                .toList();
    }

    @GetMapping("/cars/{id}")
    @Operation(summary = "Pobiera samochód po ID")
    public CarDTO findAutoById(@PathVariable Long id) {
        Car car = carService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("No Car found with id " + id));
        return CarMapper.mapToDTO(car);
    }

}

