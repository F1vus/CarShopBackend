package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.repository.UserRepository;
import edu.team.carshopbackend.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles/")
public class ProfileController {

    private final ProfileService profileService;
    private final UserRepository userRepository;

    public ProfileController(ProfileService profileService, UserRepository userRepository) {
        this.profileService = profileService;
        this.userRepository = userRepository;
    }

    @PostMapping("/rate/{userId}")
    public ResponseEntity<Double> rateUser(@PathVariable Long userId,
                                           @RequestBody double rating) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile updatedProfile = profileService.rateUser(user, rating);
        return ResponseEntity.ok(updatedProfile.getRating());
    }

    @GetMapping("/rate/{userId}")
    public ResponseEntity<Double> getUserRate(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileService.getProfileByUser(user);
        return ResponseEntity.ok(profile.getRating());
    }
}
