package edu.team.carshopbackend.service;

import edu.team.carshopbackend.dto.AuthDTO.ChangePasswordRequestDTO;
import edu.team.carshopbackend.dto.AuthDTO.UpdateEmailRequestDTO;
import edu.team.carshopbackend.dto.AuthDTO.UpdateProfileNameRequestDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.error.exception.NotFoundException;
import edu.team.carshopbackend.error.exception.UnauthorizedException;
import edu.team.carshopbackend.repository.ProfileRepository;
import edu.team.carshopbackend.service.impl.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    // --- OCENIANIE PROFILU ---
    @Transactional
    public Profile rateProfile(Long profileId, double rating) throws Exception {
        if (rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
        }

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found by id: " + profileId));

        double totalRating = profile.getRating() * profile.getRatingCount() + rating;
        profile.setRatingCount(profile.getRatingCount() + 1);
        profile.setRating(totalRating / profile.getRatingCount());

        return profileRepository.save(profile);
    }

    public double getRating(Long profileId) throws NotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found by id: " + profileId));
        return profile.getRating();
    }

    public Profile getProfileByUserId(Long userId) throws NotFoundException {
        return userService.getUserById(userId).getProfile();
    }

    public List<Car> getProfileCars(Long profileId) throws NotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found by id: " + profileId));
        return profile.getCars();
    }

    // --- RESETOWANIE HASŁA ---
    public void resetPassword(String email) throws NotFoundException {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new NotFoundException("User with email not found");
        }
        var token = tokenService.createToken(user);
        emailService.sendVerificationEmail(email, token);
    }

    // --- ZMIANA HASŁA ---
    @Transactional
    public void changePassword(Long userId, ChangePasswordRequestDTO dto)
            throws UnauthorizedException, NotFoundException {

        // Pobranie użytkownika po ID (rzuca NotFoundException jeśli nie istnieje)
        User user = userService.getUserById(userId);

        // Sprawdzenie poprawności starego hasła
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new UnauthorizedException("Old password is incorrect");
        }

        // Zakodowanie nowego hasła i zapis użytkownika
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userService.saveUser(user);
    }



    // --- ZMIANA EMAILA ---
    @Transactional
    public void changeEmail(Long userId, UpdateEmailRequestDTO dto) throws NotFoundException {
        User user = userService.getUserById(userId);
        user.setEmail(dto.getNewEmail());
        userService.saveUser(user);
    }

    // --- ZMIANA NAZWY PROFILU ---
    @Transactional
    public Profile changeProfileName(Long userId, UpdateProfileNameRequestDTO dto) throws NotFoundException {
        Profile profile = getProfileByUserId(userId);
        profile.setName(dto.getNewName());
        return profileRepository.save(profile);
    }
}
