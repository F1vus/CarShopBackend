package edu.team.carshopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupDTO {
    private String username;
    private String email;
    private String password;
}
