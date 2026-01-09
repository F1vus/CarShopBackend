package edu.team.carshopbackend.mapper.impl;

import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarMapper implements Mapper<Car, CarDTO> {

    private final ModelMapper modelMapper;

    @Override
    public CarDTO mapTo(Car car) {
        if (car == null) return null;

        CarDTO carDTO = modelMapper.map(car, CarDTO.class);

        // guard nulls: mapped owner/profile might be null depending on mapping configuration
        if (car.getOwner() != null && car.getOwner().getUser() != null && carDTO.getOwner() != null) {
            carDTO.getOwner().setEmail(car.getOwner().getUser().getEmail());
        }

        carDTO.setHadAccidents(car.getHadAccidents());
        return carDTO;
    }

    @Override
    public Car mapFrom(CarDTO carDTO) {
        if (carDTO == null) return null;

        Car car = modelMapper.map(carDTO, Car.class);
        car.setHadAccidents(carDTO.getHadAccidents());
        return car;
    }
}