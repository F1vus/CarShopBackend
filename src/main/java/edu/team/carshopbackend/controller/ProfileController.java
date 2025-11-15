package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles/")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/rate/{profileId}")
    public double rateProfile(@PathVariable Long profileId, @RequestBody double rating) {
        Profile updatedProfile = profileService.rateProfile(profileId, rating);
        return updatedProfile.getRating();
    }

    @GetMapping("/rate/{profileId}")
    public double gerProfileRate(@PathVariable Long profileId) {
        return profileService.getRating(profileId);
    }
}
