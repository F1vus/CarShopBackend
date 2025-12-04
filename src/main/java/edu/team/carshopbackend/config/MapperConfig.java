package edu.team.carshopbackend.config;

import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Profile;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Profile, ProfileDTO>() {
            @Override
            protected void configure() {
                map().setEmail(source.getUser().getEmail());
                map().setName(source.getName());
                map().setPhoneNumber(source.getPhoneNumber());
                map().setProfileImage(source.getProfileImage());
                map().setId(source.getId());
            }
        });

        return modelMapper;
    }
}
