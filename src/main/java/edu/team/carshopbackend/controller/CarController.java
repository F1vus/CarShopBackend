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

    @PostMapping("/createCar")
    public CarDTO createCar(@RequestBody CarDTO carDTO) {
       Car savedCar = carService.createCars(CarMapper.mapToEntity(carDTO));
       return CarMapper.mapToDTO(savedCar);
    }


    @GetMapping("/cars")
    public List<CarDTO> findAllAutos() {
        return carService.getAllCars().stream()
                .map(CarMapper::mapToDTO)
                .toList();
    }

    @GetMapping("/cars/{id}")
    public CarDTO findAutoById(@PathVariable Long id) {
        Car car = carService.getCarsById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
        return CarMapper.mapToDTO(car);
    }


    @PatchMapping("/cars/{id}")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        if (!carService.isExists(id)) {
            throw new RuntimeException("Car not found with id " + id);
        }

        Car carEntity = CarMapper.mapToEntity(carDTO);
        Car updatedCar = carService.CarUpdate(id, carEntity);
        return CarMapper.mapToDTO(updatedCar);
    }




    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

//    @DeleteMapping("/delete")
//    public void deleteAllCars() {
//        carService.deleteAllData();
//    }



// fixet controllers



}
