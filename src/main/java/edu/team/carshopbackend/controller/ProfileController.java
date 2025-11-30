package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.impl.UserDetailsImpl;
import edu.team.carshopbackend.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PreAuthorize("isAuthenticated()")
    @PatchMapping
    @Operation(summary = "Aktualizuje profil użytkownika częściowo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profil został zaktualizowany"),
            @ApiResponse(responseCode = "404", description = "Profil nie został znaleziony"),
            @ApiResponse(responseCode = "400", description = "Błąd walidacji danych")
    })
    public ProfileDTO patchProfile(
            @AuthenticationPrincipal UserDetailsImpl principal,
            @RequestBody ProfileDTO dto
    ) {
        Profile updatedProfile = profileService.patchProfile(principal.getId(), dto);
        return new ProfileDTO(updatedProfile);
    }
}

