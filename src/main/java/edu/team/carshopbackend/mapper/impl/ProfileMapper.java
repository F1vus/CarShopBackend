package edu.team.carshopbackend.mapper.impl;

import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileMapper implements Mapper<Profile, ProfileDTO> {
    private final ModelMapper modelMapper;

    @Override
    public ProfileDTO mapTo(Profile source) {
        ProfileDTO dto = modelMapper.map(source, ProfileDTO.class);
        dto.setEmail(source.getUser().getEmail());
        return dto;
    }

    @Override
    public Profile mapFrom(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, Profile.class);
    }
}
