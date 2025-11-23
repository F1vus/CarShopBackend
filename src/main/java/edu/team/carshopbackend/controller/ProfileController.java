package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
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
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final CarMapper carMapper;

    @PostMapping("/rate/{profileId}")
    public double rateProfile(@PathVariable Long profileId, @RequestBody double rating) {
        Profile updatedProfile = profileService.rateProfile(profileId, rating);
        return updatedProfile.getRating();
    }

    @GetMapping("/rate/{profileId}")
    public double gerProfileRate(@PathVariable Long profileId) {
        return profileService.getRating(profileId);
    }

    @GetMapping
    public ProfileDTO getProfile(@AuthenticationPrincipal UserDetailsImpl principal) {
        Profile userProfile = profileService.getProfileByUserId(principal.getId());
        return new ProfileDTO(userProfile);
    }

    @GetMapping("/cars")
    public List<CarDTO> getProfileCars(@AuthenticationPrincipal UserDetailsImpl principal) {
        return profileService.getUserCars(principal.getId()).stream()
                .map(carMapper::mapTo)
                .toList();
    }
}
