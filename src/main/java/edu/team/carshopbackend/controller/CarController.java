package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.mapper.impl.CarMapper;
import edu.team.carshopbackend.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Tag(name = "Car Controller", description = "Zarządza samochodami w systemie")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @Operation(
            summary = "Tworzy nowy samochód",
            description = "Dodaje nowy samochód do systemu i zwraca jego szczegóły"
    )
    @PostMapping("/cars")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
        Car savedCar = carService.createProduct(carMapper.mapFrom(carDTO));
        return carMapper.mapTo(savedCar);
    }

    @Operation(
            summary = "Pobiera listę wszystkich samochodów",
            description = "Zwraca wszystkie samochody w systemie jako lista CarDTO"
    )
    @GetMapping("/cars")
    public List<CarDTO> findAllAutos() {
        return carService.getAllProducts().stream()
                .map(carMapper::mapTo)
                .toList();
    }

    @Operation(
            summary = "Pobiera samochód po ID",
            description = "Zwraca samochód o podanym ID lub 404 jeśli nie istnieje"
    )
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDTO> findAutoById(@PathVariable Long id) {
        Optional<Car> car = carService.getProductById(id);
        if(car.isPresent()) {
            return ResponseEntity.ok().body(carMapper.mapTo(car.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Aktualizuje dane samochodu",
            description = "Modyfikuje istniejący samochód o podanym ID i zwraca zaktualizowane dane"
    )
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

    @Operation(
            summary = "Usuwa samochód",
            description = "Usuwa samochód o podanym ID z systemu"
    )
    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

}
