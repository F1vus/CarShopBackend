package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.CarDTO;
import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.impl.UserDetailsImpl;
import edu.team.carshopbackend.mapper.impl.CarMapper;
import edu.team.carshopbackend.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final CarMapper carMapper;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/rate/{profileId}")
    public double rateProfile(@PathVariable Long profileId, @RequestBody double rating) {
        Profile updatedProfile = profileService.rateProfile(profileId, rating);
        return updatedProfile.getRating();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/rate/{profileId}")
    public double gerProfileRate(@PathVariable Long profileId) {
        return profileService.getRating(profileId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ProfileDTO getProfile(@AuthenticationPrincipal UserDetailsImpl principal) {
        Profile userProfile = profileService.getProfileByUserId(principal.getId());
        return new ProfileDTO(userProfile);
    }

    @GetMapping("/{profileId}/cars")
    public List<CarDTO> getProfileCars(@PathVariable Long profileId) {
        return profileService.getProfileCars(profileId).stream()
                .map(carMapper::mapTo)
                .toList();
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping
    @Operation(summary = "Partially updates the user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile has been updated"),
            @ApiResponse(responseCode = "404", description = "Profile not found"),
            @ApiResponse(responseCode = "400", description = "Data validation error")
    })
    public ProfileDTO patchProfile(
            @AuthenticationPrincipal UserDetailsImpl principal,
            @RequestBody ProfileDTO dto
    ) {
        Profile updatedProfile = profileService.patchProfile(principal.getId(), dto);
        return new ProfileDTO(updatedProfile);
    }
}