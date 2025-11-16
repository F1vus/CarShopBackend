package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.dto.CarSuggestionDTO;
import edu.team.carshopbackend.dto.PhotoDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.mapper.impl.CarMapper;
import edu.team.carshopbackend.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Tag(name = "Car Controller", description = "Manages cars in the system")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping("/cars")
    @Operation(summary = "Creates a new car", description = "Creates a new car in the system based on the submitted DTO data and returns the created object")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
        Car savedCar = carService.createProduct(carMapper.mapFrom(carDTO));
        return carMapper.mapTo(savedCar);
    }

    @GetMapping("/cars")
    @Operation(summary = "Retrieves a list of all cars", description = "Returns a list of all cars registered in the system.")
    public List<CarDTO> findAllAutos() {
        return carService.getAllProducts().stream()
                .map(carMapper::mapTo)
                .toList();
    }

    @GetMapping("/cars/{id}")
    @Operation(summary = "Picks up the car by ID", description = "Returns the car with the specified ID, if it exists. If the car does not exist, returns status 404.")
    public CarDTO findAutoById(@PathVariable Long id) {
        Car car = carService.getProductById(id);
        return carMapper.mapTo(car);
    }

    @PatchMapping("/cars/{id}")
    @Operation(summary = "Updates the car", description = "Updates the existing car with the specified ID. Returns the updated object or throws an exception if the car does not exist.")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        carDTO.setId(id);

        Car carEntity = carMapper.mapFrom(carDTO);
        Car updatedCar = carService.carUpdate(id, carEntity);
        return carMapper.mapTo(updatedCar);
    }

    @DeleteMapping("/cars/{id}")
    @Operation(summary = "Removes the car", description = "Removes the vehicle with the specified ID from the system.")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

    @GetMapping("/cars/suggestions")
    @Operation(summary = "Tips for naming cars", description = "car return list")
    public List<CarSuggestionDTO> suggestionCar(@RequestParam String query) {
        if (query.length() < 2) {
            return List.of();
        }

        return carService.suggestCar(query)
                .stream()
                .map(car -> new CarSuggestionDTO(
                        car.getId(),
                        car.getName(),
                        car.getPrice(),
                        car.getPhotos()
                                .stream()
                                .map(photo -> new PhotoDTO(photo.getId(), photo.getUrl())).toList()
                        ))
                .distinct()
                .limit(10)
                .toList();
    }

}

