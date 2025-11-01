package edu.team.carshopbackend.controller;

import edu.team.carshopbackend.config.jwtConfig.JwtCore;
import edu.team.carshopbackend.dto.LoginDTO;
import edu.team.carshopbackend.dto.SignupDTO;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.service.impl.UserService;
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

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication;

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        String jwt = jwtCore.generateToken(authentication);

        log.info("User logged Name: {}", authentication.getName());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<String> signup(@RequestBody SignupDTO signupDTO){
        User user = new User();
        
        user.setUsername(signupDTO.getUsername());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        userService.register(user);

        log.info("Registered new user  Id: {}, Name: {}", user.getId(), user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body("Created user successfully");
    }
}
