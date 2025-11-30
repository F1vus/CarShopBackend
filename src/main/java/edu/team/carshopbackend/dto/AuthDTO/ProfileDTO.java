package edu.team.carshopbackend.dto.AuthDTO;

import edu.team.carshopbackend.entity.Profile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {

    @Schema(description = "ID profilu")
    private Long id;

    @Schema(description = "Nazwa profilu")
    private String name;

    @Schema(description = "Telefon użytkownika")
    private String phoneNumber;

    @Schema(description = "Email użytkownika")
    private String email;

    @Schema(description = "URL zdjęcia profilowego")
    private String profileImage;

    public ProfileDTO(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.email = profile.getEmail();
        this.phoneNumber = profile.getPhoneNumber();
        this.profileImage = profile.getProfileImage();
    }
}
