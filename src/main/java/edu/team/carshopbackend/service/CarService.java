package edu.team.carshopbackend.service;

import edu.team.carshopbackend.client.PhotoClient;
import edu.team.carshopbackend.client.UploadPhotoResponse;
import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.dto.CreateCarRequestDTO;
import edu.team.carshopbackend.entity.*;
import edu.team.carshopbackend.error.exception.NotFoundException;
import edu.team.carshopbackend.mapper.impl.CarMapper;
import edu.team.carshopbackend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ColorRepository colorRepository;
    private final PetrolRepository petrolRepository;
    private final CarProducerRepository carProducerRepository;
    private final PhotoRepository photoRepository;
    private final CarMapper carMapper;
    private final PhotoClient photoClient;


    @Transactional
    public Car createProduct(Car car) {
        car.getPhotos().forEach(photo -> photo.setCar(car));
        return carRepository.save(car);
    }

    public List<Car> getAllProducts() {
        return carRepository.findAll();
    }

    public Car getProductById(Long id) throws NotFoundException {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found"));
    }

    public List<Car> suggestCar(String query) {
        return carRepository.findByNameContainingIgnoreCase(query);
    }

    public Car carUpdate(Long id, Car car) throws NotFoundException {
        car.setId(id);

        return carRepository.findById(id).map(existingCar -> {
            Optional.ofNullable(car.getName()).ifPresent(existingCar::setName);
            Optional.ofNullable(car.getPrice()).ifPresent(existingCar::setPrice);
            Optional.ofNullable(car.getDescription()).ifPresent(existingCar::setDescription);
            Optional.ofNullable(car.getColor()).ifPresent(existingCar::setColor);
            Optional.ofNullable(car.getMileage()).ifPresent(existingCar::setMileage);
            Optional.ofNullable(car.getCarState()).ifPresent(existingCar::setCarState);
            Optional.ofNullable(car.getPetrolType()).ifPresent(existingCar::setPetrolType);
            Optional.ofNullable(car.getEngineCapacity()).ifPresent(existingCar::setEngineCapacity);
            Optional.ofNullable(car.getPower()).ifPresent(existingCar::setPower);
            Optional.ofNullable(car.getYear()).ifPresent(existingCar::setYear);
            Optional.ofNullable(car.getPhotos()).ifPresent(existingCar::setPhotos);
            Optional.ofNullable(car.getProducent()).ifPresent(existingCar::setProducent);
            Optional.ofNullable(car.getHadAccidents()).ifPresent(existingCar::setHadAccidents);
            return carRepository.save(existingCar);
        }).orElseThrow(() -> new NotFoundException("Car does not exist with id " + id));
    }

    public boolean isExists(Long id) {
        return carRepository.existsById(id);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Transactional
    public CarDTO createCarWithPhotos(CreateCarRequestDTO req, List<MultipartFile> photos, Profile owner) {
        Car car = carMapper.mapFrom(req);

        if (req.getColor() != null) {
            Color color = colorRepository.findById(req.getColor())
                    .orElseThrow(() -> new NotFoundException("Color not found"));
            car.setColor(color);
        }
        if (req.getPetrolType() != null) {
            Petrol petrol = petrolRepository.findById(req.getPetrolType())
                    .orElseThrow(() -> new NotFoundException("Petrol not found"));
            car.setPetrolType(petrol);
        }
        if (req.getProducent() != null) {
            CarProducent producent = carProducerRepository.findById(req.getProducent())
                    .orElseThrow(() -> new NotFoundException("Producent not found"));
            car.setProducent(producent);
        }

        car.setOwner(owner);

        car = carRepository.save(car);

        if (photos != null && !photos.isEmpty()) {
            for (MultipartFile file : photos) {
                try{
                    UploadPhotoResponse photoResponse = photoClient.uploadPhoto(car.getId(), file);

                    Photo photoEntity = new Photo();
                    photoEntity.setCar(car);
                    photoEntity.setUrl(photoResponse.getUrl());

                    photoRepository.save(photoEntity);
                } catch (IOException e){
                    throw new RuntimeException("Failed to upload photo", e);
                }
            }
        }

        return carMapper.mapTo(car);
    }

}
