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
                .color(car.getColor().getName())
                .mileage(car.getMileage())
                .state(car.getCar_status())
                .petrolType(car.getPetrolType().getName())
                .engineCapacity(car.getEngine_capacity())
                .power(car.getPower())
                .year(car.getYear())
                .imageUrl(car.getImageUrl())
                .build();
    }

}
