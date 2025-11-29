package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.impl.UserDetailsImpl;
import edu.team.carshopbackend.mapper.impl.CarMapper;
import edu.team.carshopbackend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    private final ProfileService profileService;
    private final CarMapper carMapper;

    // ==========================
    // UPDATE PROFILE
    // ==========================
    @PutMapping("/update")
    public ProfileDTO updateProfile(@AuthenticationPrincipal UserDetailsImpl principal,
                                    @RequestBody ProfileDTO dto) {

        Profile updated = profileService.updateProfile(principal.getId(), dto);
        return new ProfileDTO(updated);
    }

    // ==========================
    // RATE PROFILE
    // ==========================
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/rate/{profileId}")
    public double rateProfile(@PathVariable Long profileId, @RequestBody double rating) {
        Profile updatedProfile = profileService.rateProfile(profileId, rating);
        return updatedProfile.getRating();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/rate/{profileId}")
    public double getProfileRate(@PathVariable Long profileId) {
        return profileService.getRating(profileId);
    }

    // ==========================
    // GET OWN PROFILE
    // ==========================
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ProfileDTO getProfile(@AuthenticationPrincipal UserDetailsImpl principal) {
        Profile userProfile = profileService.getProfileByUserId(principal.getId());
        return new ProfileDTO(userProfile);
    }

    // ==========================
    // GET CARS OF PROFILE
    // ==========================
    @GetMapping("/{profileId}/cars")
    public List<CarDTO> getProfileCars(@PathVariable Long profileId) {
        return profileService.getProfileCars(profileId).stream()
                .map(carMapper::mapTo)
                .toList();
    }
}
