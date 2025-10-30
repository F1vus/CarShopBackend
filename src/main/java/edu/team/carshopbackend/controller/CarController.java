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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<CarDTO> findAllAutos() {
        return carService.getAllProducts().stream()
                .map(CarMapper::mapToDTO)
                .toList();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDTO> findAutoById(@PathVariable Long id) {
        Optional<Car> car = carService.getProductById(id);
        if(car.isPresent()) {
            return ResponseEntity.ok().body(CarMapper.mapToDTO(car.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

