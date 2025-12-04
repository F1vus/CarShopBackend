package edu.team.carshopbackend.service;

import edu.team.carshopbackend.dto.AuthDTO.ChangePasswordRequestDTO;
import edu.team.carshopbackend.dto.AuthDTO.UpdateEmailRequestDTO;
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

    @Transactional
    public Profile rateProfile(Long profileId, double rating) {
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

    @Transactional
    public void resetPassword(String email) throws NotFoundException {
        User user = userService.getUserByEmail(email);

        var token = tokenService.createToken(user);
        emailService.sendVerificationEmail(email, token);
    }

    @Transactional
    public void changePassword(Long userId, ChangePasswordRequestDTO dto)
            throws UnauthorizedException, NotFoundException {

        User user = userService.getUserById(userId);

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new UnauthorizedException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userService.saveUser(user);
    }

    @Transactional
    public void changeEmail(Long userId, UpdateEmailRequestDTO dto) throws NotFoundException {
        User user = userService.getUserById(userId);
        user.setEmail(dto.getNewEmail());
        userService.saveUser(user);
    }
}
