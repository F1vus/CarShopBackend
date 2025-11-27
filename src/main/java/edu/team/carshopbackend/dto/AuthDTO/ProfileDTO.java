package edu.team.carshopbackend.dto.AuthDTO;


import edu.team.carshopbackend.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private Long id;
    private String name;

    public ProfileDTO(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
    }
}
