package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.dto.AuthDTO.*;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.service.AuthenticationService;
import edu.team.carshopbackend.service.EmailVerificationTokenService;
import edu.team.carshopbackend.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final EmailVerificationTokenService emailVerificationTokenService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "login of user with(email,password), and return AuthenticationResponseDTO")
    public AuthenticationResponseDTO login(@RequestBody LoginDTO loginDTO) {
        return authenticationService.authenticate(loginDTO);
    }

    @PostMapping("/register")
    @Operation(summary = "User registration", description = "registers of new user with (username,email, and password), and return string-success")
    public ResponseEntity<String> signup(@RequestBody SignupDTO signupDTO){
        String registerResult  = authenticationService.register(signupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResult);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody VerifyRequestDTO req) {
        var token = emailVerificationTokenService.getToken(req.getToken(), userService.getUserByEmail(req.getEmail()));
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Expired code");
        }

        User user = token.getUser();
        user.setEnabled(true);

        userService.updateUser(user);
        emailVerificationTokenService.deleteToken(token);

        return ResponseEntity.ok("Email confirmed!");
    }

    @PostMapping("/reset-verify")
    public ResponseEntity<String> resetVerify(@RequestBody ResetVerifyRequestDTO req) {
        emailVerificationTokenService.resetVerificationToken(userService.getUserByEmail(req.getEmail()));
        return ResponseEntity.ok("New token sent");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponseDTO> refresh(HttpServletRequest request) {
        AuthenticationResponseDTO dto = authenticationService.refreshToken(request);
        return ResponseEntity.ok(dto);
    }
}
