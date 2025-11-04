package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.mapper.impl.CarMapper;
import edu.team.carshopbackend.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Tag(name = "Car Controller", description = "Zarządza samochodami w systemie")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @PostMapping("/cars")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
       Car savedCar = carService.createProduct(carMapper.mapFrom(carDTO));
       return carMapper.mapTo(savedCar);
    }


    @GetMapping("/cars")
    @Operation(summary = "Pobiera listę wszystkich samochodów")
    public List<CarDTO> findAllAutos() {
        return carService.getAllProducts().stream()
                .map(carMapper::mapTo)
                .toList();
    }

    @GetMapping("/cars/{id}")
    @Operation(summary = "Pobiera samochód po ID")
    public ResponseEntity<CarDTO> findAutoById(@PathVariable Long id) {
        Optional<Car> car = carService.getProductById(id);
        if(car.isPresent()) {
            return ResponseEntity.ok().body(carMapper.mapTo(car.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/cars/{id}")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        if (!carService.isExists(id)) {
            throw new RuntimeException("Car not found with id " + id);
        }
        carDTO.setId(id);

        Car carEntity = carMapper.mapFrom(carDTO);
        Car updatedCar = carService.carUpdate(id, carEntity);
        return carMapper.mapTo(updatedCar);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

}

