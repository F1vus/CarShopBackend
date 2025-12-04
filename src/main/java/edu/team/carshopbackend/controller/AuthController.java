package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.config.jwtConfig.JwtCore;
import edu.team.carshopbackend.dto.AuthDTO.LoginDTO;
import edu.team.carshopbackend.dto.AuthDTO.ResetVerifyRequestDTO;
import edu.team.carshopbackend.dto.AuthDTO.SignupDTO;
import edu.team.carshopbackend.dto.AuthDTO.VerifyRequestDTO;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.service.TokenService;
import edu.team.carshopbackend.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "login of user with(email,password), and return string-success(JWT-token)")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        String jwt = jwtCore.generateToken(authentication);

        log.info("User logged by email: {}", authentication.getName());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    @Operation(summary = "User registration", description = "registers of new user with (username,email, and password), and return string-success")
    public ResponseEntity<String> signup(@RequestBody SignupDTO signupDTO){
        User user = new User();

        user.setEmail(signupDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        userService.register(user, signupDTO.getUsername());

        log.info("Registered new user  Id: {}, Email: {}", user.getId(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body("Created user successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody VerifyRequestDTO req) {
        var token = tokenService.getToken(req.getToken(), userService.getUserByEmail(req.getEmail()));
        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Expired code");
        }

        User user = token.getUser();
        user.setEnabled(true);

        userService.saveUser(user);
        tokenService.deleteToken(token);

        return ResponseEntity.ok("Email confirmed");
    }

    @PostMapping("/reset-verify")
    public ResponseEntity<String> resetVerify(@RequestBody ResetVerifyRequestDTO req) {
        tokenService.resetVerificationToken(userService.getUserByEmail(req.getEmail()));
        return ResponseEntity.ok("New token sent");
    }
}
