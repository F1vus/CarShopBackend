package edu.team.carshopbackend.service.impl;

import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.repository.ProfileRepository;
import edu.team.carshopbackend.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public Profile updateProfile(Long userId, ProfileDTO dto) {
        Profile profile = getProfileByUserId(userId);

        if (dto.getName() != null) profile.setName(dto.getName());
        if (dto.getPhoneNumber() != null) profile.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getEmail() != null) profile.setEmail(dto.getEmail());
        if (dto.getProfileImage() != null) profile.setProfileImage(dto.getProfileImage());

        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        return profileRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Profile not found for userId: " + userId));
    }

    @Override
    public List<Car> getProfileCars(Long profileId) {
        Profile profile = getProfileByUserId(profileId); // metoda już istnieje
        return profile.getCars(); // zwraca listę samochodów powiązanych z profilem
    }


    @Override
    @Transactional
    public Profile rateProfile(Long profileId, double rating) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NoSuchElementException("Profile not found for profileId: " + profileId));

        double newRating = ((profile.getRating() * profile.getRatingCount()) + rating) / (profile.getRatingCount() + 1);
        profile.setRating(newRating);
        profile.setRatingCount(profile.getRatingCount() + 1);

        return profileRepository.save(profile);
    }

    @Override
    public double getRating(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NoSuchElementException("Profile not found for profileId: " + profileId));
        return profile.getRating();
    }
}
