package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.mapper.CarMapper;
import edu.team.carshopbackend.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<CarDTO> findAutoById(@PathVariable Long id) {
        Optional<Car> car = carService.getProductById(id);
        if(car.isPresent()) {
            return ResponseEntity.ok().body(CarMapper.mapToDTO(car.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

