package edu.team.carshopbackend.service;

import edu.team.carshopbackend.dto.AuthDTO.ChangePasswordRequestDTO;
import edu.team.carshopbackend.dto.AuthDTO.UpdateEmailRequestDTO;
import edu.team.carshopbackend.dto.AuthDTO.ProfileDTO;
import edu.team.carshopbackend.entity.Car;
import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.error.exception.NotFoundException;
import edu.team.carshopbackend.error.exception.ChangePasswordException;
import edu.team.carshopbackend.repository.ProfileRepository;
import edu.team.carshopbackend.service.impl.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Profile updateProfile(Long userId, ProfileDTO dto) {
        Profile profile = getProfileByUserId(userId);

        if (dto.getName() != null) profile.setName(dto.getName());
        if (dto.getPhoneNumber() != null) profile.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getProfileImage() != null) profile.setProfileImage(dto.getProfileImage());

        return profileRepository.save(profile);
    }

    public Profile save(Profile profile) {
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

        var token = emailVerificationTokenService.createToken(user);
        emailService.sendVerificationEmail(email, token);
    }

    @Transactional
    public void changePassword(Long userId, ChangePasswordRequestDTO dto)
            throws ChangePasswordException, NotFoundException {

        User user = userService.getUserById(userId);

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new ChangePasswordException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userService.updateUser(user);
    }

    @Transactional
    public void changeEmail(Long userId, UpdateEmailRequestDTO dto) throws NotFoundException {
            User user = userService.getUserById(userId);
            user.setEmail(dto.getNewEmail());
            userService.updateUser(user);
    }

    @Transactional
    public Profile rateProfile(Long profileId, double rating) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new NoSuchElementException("Profile not found for profileId: " + profileId));

        double newRating = ((profile.getRating() * profile.getRatingCount()) + rating) / (profile.getRatingCount() + 1);
        profile.setRating(newRating);
        profile.setRatingCount(profile.getRatingCount() + 1);

        return profileRepository.save(profile);
    }
}
