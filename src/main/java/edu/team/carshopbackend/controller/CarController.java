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

    @PostMapping("/c")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
       Car savedCar = carService.createProduct(CarMapper.mapToEntity(carDTO));
       return CarMapper.mapToDTO(savedCar);
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
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
        return CarMapper.mapToDTO(car);
    }


    @PatchMapping("/changeCarAdress/{id}")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        Car existingCar = carService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));

        if (carDTO.getName() != null) existingCar.setName(carDTO.getName());
        if (carDTO.getPrice() != null) existingCar.setPrice(carDTO.getPrice());
        if (carDTO.getDescription() != null) existingCar.setDescription(carDTO.getDescription());
        if(carDTO.getMileage() != null) existingCar.setMileage(carDTO.getMileage());
        if(carDTO.getState()!= null) existingCar.setCar_status(carDTO.getState());
        if(carDTO.getEngineCapacity() != null) existingCar.setEngine_capacity(carDTO.getEngineCapacity());
        if(carDTO.getPower() != null) existingCar.setPower(carDTO.getPower());
        if(carDTO.getYear() != null) existingCar.setYear(carDTO.getYear());
        if(carDTO.getId() != null) existingCar.setId(carDTO.getId());

        Car savedCar = carService.updateProduct(existingCar);
        return CarMapper.mapToDTO(savedCar);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

    @DeleteMapping("/delete")
    public void deleteAllCars() {
        carService.deleteAllData();
    }







}
