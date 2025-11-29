package edu.team.carshopbackend.service;

import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.entity.Profile;
import java.util.List;

public interface ProfileService {

    Profile updateProfile(Long userId, ProfileDTO dto);

    Profile getProfileByUserId(Long userId);

    List<Car> getProfileCars(Long profileId);

    Profile rateProfile(Long profileId, double rating);

    double getRating(Long profileId);
}
