package edu.team.carshopbackend.entity.enums.converter;

import edu.team.carshopbackend.entity.enums.CarState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CarStateConverter implements AttributeConverter<CarState, String> {
    @Override
    public String convertToDatabaseColumn(CarState attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public CarState convertToEntityAttribute(String dbData) {
        return dbData == null ? null : CarState.fromString(dbData);
    }
}

