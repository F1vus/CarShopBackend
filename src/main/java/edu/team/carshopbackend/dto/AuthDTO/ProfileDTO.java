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

    @Schema(description = "Profile ID")
    private Long id;

    @Schema(description = "Profile name")
    private String name;

    @Schema(description = "User's phone")
    private String phoneNumber;

    @Schema(description = "User email")
    private String email;

    @Schema(description = "Profile picture URL")
    private String profileImage;

    public ProfileDTO(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.email = profile.getEmail();
        this.phoneNumber = profile.getPhoneNumber();
        this.profileImage = profile.getProfileImage();
    }
}
