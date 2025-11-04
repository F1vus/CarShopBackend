package edu.team.carshopbackend.mapper;

import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Car;

public class CarMapper {

    private CarMapper() {}

    public static CarDTO mapToDTO(Car car) {
        if (car == null) {
            return null;
        }

        return CarDTO.builder()
                .id(car.getId())
                .name(car.getName())
                .price(car.getPrice())
                .description(car.getDescription())
                .color(car.getColor() != null ? car.getColor().getName() : null)
                .mileage(car.getMileage())
                .state(car.getCar_status())
                .petrolType(car.getPetrolType() != null ? car.getPetrolType().getName() : null)
                .engineCapacity(car.getEngine_capacity())
                .power(car.getPower())
                .year(car.getYear())
                .imageUrl(car.getImageUrl())
                .build();
    }


    public static Car mapToEntity(CarDTO carDTO) {
        if (carDTO == null) {
            return null;
        }

        Car car = new Car();
        //car.setId(dto.getId());
        car.setName(carDTO.getName());
        car.setPrice(carDTO.getPrice());
        car.setDescription(carDTO.getDescription());
//    car.setColor(dto.getColor());
        car.setMileage(carDTO.getMileage());
        car.setCar_status(carDTO.getState());
//    car.setPetrolType(dto.getPetrolType());
        car.setEngine_capacity(carDTO.getEngineCapacity());
        car.setPower(carDTO.getPower());
        car.setYear(carDTO.getYear());
        car.setImageUrl(carDTO.getImageUrl());

        return car;



    }

}
