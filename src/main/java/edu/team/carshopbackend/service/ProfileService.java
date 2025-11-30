package edu.team.carshopbackend.service;

import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.error.exception.NotFoundException;
import edu.team.carshopbackend.error.exception.RatingRangeException;
import edu.team.carshopbackend.repository.ProfileRepository;
import edu.team.carshopbackend.service.impl.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserService userService;

    @Transactional
    public Profile rateProfile(Long profileId, double rating) throws RatingRangeException, NotFoundException {
        if (rating < 1.0 || rating > 5.0) {
            throw new RatingRangeException("Rating must be between 1.0 and 5.0");
        }

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found by id: " + profileId));

        double totalRating = profile.getRating() * profile.getRatingCount() + rating;
        profile.setRatingCount(profile.getRatingCount() + 1);
        profile.setRating(totalRating / profile.getRatingCount());

        return profileRepository.save(profile);
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public double getRating(Long profileId) throws NotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found by id: " + profileId));

        return profile.getRating();
    }

    public List<Car> getProfileCars(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new NotFoundException("Profile not found by id: " + profileId));
        return profile.getCars();
    }

    @Transactional
    public Profile patchProfile(Long userId, ProfileDTO dto) {
        Profile profile = getProfileByUserId(userId);

        // Update only submitted fields
        if (dto.getName() != null) {
            profile.setName(dto.getName());
        }

        if (dto.getEmail() != null) {
            if (profileRepository.existsByEmail(dto.getEmail()) &&
                    !dto.getEmail().equals(profile.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            profile.setEmail(dto.getEmail());
        }

        if (dto.getPhoneNumber() != null) {
            profile.setPhoneNumber(dto.getPhoneNumber());
        }

        if (dto.getProfileImage() != null) {
            profile.setProfileImage(dto.getProfileImage());
        }

        return profileRepository.save(profile);
    }

    public Profile getProfileByUserId(Long userId) {
        return userService.getUserById(userId).getProfile();
    }
}
