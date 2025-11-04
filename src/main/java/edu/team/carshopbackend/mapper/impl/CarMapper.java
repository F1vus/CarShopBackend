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
        return modelMapper.map(car, CarDTO.class);
    }

    @Override
    public Car mapFrom(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }
}