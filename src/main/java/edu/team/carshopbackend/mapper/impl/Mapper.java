package edu.team.carshopbackend.mapper.impl;

import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public ProfileDTO toProfileDto(Profile profile) {
        if (profile == null) {
            return null;
        }

        ProfileDTO dto = new ProfileDTO();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setPhoneNumber(profile.getPhoneNumber());
        dto.setEmail(profile.getEmail());
        dto.setProfileImage(profile.getProfileImage());

        return dto;
    }

    public Profile toProfile(ProfileDTO dto) {
        if (dto == null) {
            return null;
        }

        Profile profile = new Profile();
        profile.setId(dto.getId());
        profile.setName(dto.getName());
        profile.setPhoneNumber(dto.getPhoneNumber());
        profile.setEmail(dto.getEmail());
        profile.setProfileImage(dto.getProfileImage());

        return profile;
    }
}
