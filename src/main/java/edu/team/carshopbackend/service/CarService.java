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

    public Car carUpdate(Long id, Car car) {
        car.setId(id);

        return carRepository.findById(id).map(existingCar -> {
            Optional.ofNullable(car.getName()).ifPresent(existingCar::setName);
            Optional.ofNullable(car.getPrice()).ifPresent(existingCar::setPrice);
            Optional.ofNullable(car.getDescription()).ifPresent(existingCar::setDescription);
            Optional.ofNullable(car.getMileage()).ifPresent(existingCar::setMileage);
            Optional.ofNullable(car.getCarState()).ifPresent(existingCar::setCarState);
            Optional.ofNullable(car.getEngineCapacity()).ifPresent(existingCar::setEngineCapacity);
            Optional.ofNullable(car.getPower()).ifPresent(existingCar::setPower);
            Optional.ofNullable(car.getYear()).ifPresent(existingCar::setYear);
            return carRepository.save(existingCar);
        }).orElseThrow(() -> new RuntimeException("Car does not exist with id " + id));
    }

    public boolean isExists(Long id) {
        return carRepository.existsById(id);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }
}
