package edu.team.carshopbackend.service;

import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createProduct(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllProducts() {
        return carRepository.findAll();
    }

    public Optional<Car> getProductById(Long id) {
        return carRepository.findById(id);
    }

}
