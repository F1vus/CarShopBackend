package edu.team.carshopbackend.service.impl;

import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.entity.impl.UserDetailsImpl;
import edu.team.carshopbackend.error.exception.NotFoundException;
import edu.team.carshopbackend.repository.ProfileRepository;
import edu.team.carshopbackend.repository.UserRepository;
import edu.team.carshopbackend.service.EmailService;
import edu.team.carshopbackend.service.TokenService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EmailService emailService;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(final String email) throws NotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        String.format("User %s not found", email)
                ));
        return UserDetailsImpl.build(user);
    }

    @Transactional
    public void register(final User user, String profileName) throws EntityExistsException {
        if(userRepository.existsUserByEmail(user.getEmail())){
            throw new EntityExistsException("Email already exists");
        }

        User savedUser = userRepository.save(user);

        Profile profile = new Profile();
        profile.setUser(savedUser);
        profile.setName(profileName);
        profileRepository.save(profile);

        emailService.sendVerificationEmail(savedUser.getEmail(), tokenService.createToken(savedUser));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserByEmail(final String email) throws NotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found by email: " + email));
    }

    public User getUserById(final Long id) throws NotFoundException {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new NotFoundException("User not found by id: " + id));
    }
}
