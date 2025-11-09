package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.mapper.impl.CarMapper;
import edu.team.carshopbackend.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @Operation(summary = "Tworzy nowy samochód", description = "Tworzy nowy samochód w systemie na podstawie przesłanych danych DTO i zwraca utworzony obiekt.")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
        Car savedCar = carService.createProduct(carMapper.mapFrom(carDTO));
        return carMapper.mapTo(savedCar);
    }

    @GetMapping("/cars")
    @Operation(summary = "Pobiera listę wszystkich samochodów", description = "Zwraca listę wszystkich samochodów zapisanych w systemie.")
    public List<CarDTO> findAllAutos() {
        return carService.getAllProducts().stream()
                .map(carMapper::mapTo)
                .toList();
    }

    @GetMapping("/cars/{id}")
    @Operation(summary = "Pobiera samochód po ID", description = "Zwraca samochód o podanym ID, jeśli istnieje. Jeśli samochód nie istnieje, zwraca status 404.")
    public ResponseEntity<CarDTO> findAutoById(@PathVariable Long id) {
        Optional<Car> car = carService.getProductById(id);
        if (car.isPresent()) {
            return ResponseEntity.ok().body(carMapper.mapTo(car.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/cars/{id}")
    @Operation(summary = "Aktualizuje samochód", description = "Aktualizuje istniejący samochód o podanym ID. Zwraca zaktualizowany obiekt lub rzuca wyjątek jeśli samochód nie istnieje.")
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
    @Operation(summary = "Usuwa samochód", description = "Usuwa samochód o podanym ID z systemu.")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
    }



    @GetMapping("/cars/suggestions")
    @Operation(summary = "Wskazówki dotyczące nazw samochodów", description = "return list samochodów")
    public List<String> suggestionCar(@RequestParam String query) { return carService.suggestCar(query).stream().map(Car::getName).distinct().limit(10).toList();} // wskazówki można zrobić więcej

}

