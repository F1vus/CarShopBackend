package edu.team.carshopbackend.service;

import edu.team.carshopbackend.config.jwtConfig.JwtCore;
import edu.team.carshopbackend.dto.AuthDTO.LoginDTO;
import edu.team.carshopbackend.dto.AuthDTO.SignupDTO;
import edu.team.carshopbackend.entity.JwtToken;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.entity.enums.JwtTokenType;
import edu.team.carshopbackend.repository.JwtTokenRepository;
import edu.team.carshopbackend.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;
    private final JwtTokenRepository jwtTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ProfileService profileService;
    private final EmailService emailService;
    private final EmailVerificationTokenService emailVerificationTokenService;


    public String authenticate(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        var user = userService.getUserByEmail(loginDTO.getEmail());

        String jwt = jwtCore.generateToken(authentication);
        saveUserJwtToken(user, jwt);
        log.info("User logged by email: {}", authentication.getName());
        return jwt;
    }

    public String register(SignupDTO signupDTO) {
        User user = new User();

        user.setEmail(signupDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        User savedUser = userService.save(user);

        Profile profile = new Profile();
        profile.setUser(savedUser);
        profile.setName(signupDTO.getUsername());
        profileService.save(profile);

        emailService.sendVerificationEmail(user.getEmail(), emailVerificationTokenService.createToken(user));

        log.info("Registered new user  Id: {}, Email: {}", user.getId(), user.getEmail());
        return "User registered successfully";
    }

    private void saveUserJwtToken(User user, String jwtToken) {
        var token = new JwtToken();
        token.setUser(user);
        token.setToken(jwtToken);
        token.setTokenType(JwtTokenType.BEARER);
        token.setRevoked(false);
        token.setExpired(false);
        jwtTokenRepository.save(token);
    }
}
