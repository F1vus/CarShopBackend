package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.AuthDTO.ChangePasswordRequestDTO;
import edu.team.carshopbackend.dto.AuthDTO.ResetVerifyRequestDTO;
import edu.team.carshopbackend.dto.AuthDTO.UpdateEmailRequestDTO;
import edu.team.carshopbackend.dto.AuthDTO.UpdateProfileNameRequestDTO;
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
public class ProfileController {

    private final ProfileService profileService;
    private final CarMapper carMapper;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/rate/{profileId}")
    public double rateProfile(@PathVariable Long profileId, @RequestBody double rating) throws Exception {
        Profile updatedProfile = profileService.rateProfile(profileId, rating);
        return updatedProfile.getRating(); // <-- zwracamy tylko double
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/rate/{profileId}")
    public double getProfileRate(@PathVariable Long profileId) throws Exception {
        return profileService.getRating(profileId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ProfileDTO getProfile(@AuthenticationPrincipal UserDetailsImpl principal) throws Exception {
        Profile userProfile = profileService.getProfileByUserId(principal.getId());
        return new ProfileDTO(userProfile);
    }

    @GetMapping("/{profileId}/cars")
    public List<CarDTO> getProfileCars(@PathVariable Long profileId) throws Exception {
        return profileService.getProfileCars(profileId).stream()
                .map(carMapper::mapTo)
                .toList(); // lub .collect(Collectors.toList()) jeśli JDK < 16
    }

    // --- NOWE ENDPOINTY ---

    // Resetowanie hasła (wysyłanie maila z tokenem)
    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetVerifyRequestDTO dto) throws Exception {
        profileService.resetPassword(dto.getEmail());
    }

    // Zmiana hasła (wymaga autoryzacji)
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change-password")
    public void changePassword(@AuthenticationPrincipal UserDetailsImpl principal,
                               @RequestBody ChangePasswordRequestDTO dto) throws Exception {
        profileService.changePassword(principal.getId(), dto);
    }

    // Zmiana maila (wymaga autoryzacji)
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/change-email")
    public void changeEmail(@AuthenticationPrincipal UserDetailsImpl principal,
                            @RequestBody UpdateEmailRequestDTO dto) throws Exception {
        profileService.changeEmail(principal.getId(), dto);
    }

    // Zmiana nazwy profilu (wymaga autoryzacji)
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update-name")
    public ProfileDTO updateProfileName(@AuthenticationPrincipal UserDetailsImpl principal,
                                        @RequestBody UpdateProfileNameRequestDTO dto) throws Exception {
        Profile updatedProfile = profileService.changeProfileName(principal.getId(), dto);
        return new ProfileDTO(updatedProfile);
    }
}
