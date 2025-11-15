package edu.team.carshopbackend.service;

import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Profile rateProfile(Long profileId, double rating) {
        if (rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found by id: " + profileId));

        double totalRating = profile.getRating() * profile.getRatingCount() + rating;
        profile.setRatingCount(profile.getRatingCount() + 1);
        profile.setRating(totalRating / profile.getRatingCount());

        return profileRepository.save(profile);
    }

    public double getRating(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found by id: " + profileId));

        return profile.getRating();
    }
}
