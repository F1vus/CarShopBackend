package edu.team.carshopbackend.entity.enums.converter;

import edu.team.carshopbackend.entity.enums.AutoState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AutoStateConverter implements AttributeConverter<AutoState, String> {
    @Override
    public String convertToDatabaseColumn(AutoState attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public AutoState convertToEntityAttribute(String dbData) {
        return dbData == null ? null : AutoState.fromString(dbData);
    }
}