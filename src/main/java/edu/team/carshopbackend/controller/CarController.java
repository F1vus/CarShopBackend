package edu.team.carshopbackend.controller;


import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.mapper.CarMapper;
import edu.team.carshopbackend.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CarDTO findAutoById(@PathVariable Long id) {
        Car car = carService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("No Car found with id " + id));
        return CarMapper.mapToDTO(car);
    }




}
