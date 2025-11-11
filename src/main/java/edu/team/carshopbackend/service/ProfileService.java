package edu.team.carshopbackend.service;

import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional
    public Profile rateUser(User user, double rating) {
        if (rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found for user"));

        double totalRating = profile.getRating() * profile.getRatingCount() + rating;
        profile.setRatingCount(profile.getRatingCount() + 1);
        profile.setRating(totalRating / profile.getRatingCount());

        return profileRepository.save(profile);
    }

    public Profile getProfileByUser(User user) {
        return profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found for user"));
    }
}
