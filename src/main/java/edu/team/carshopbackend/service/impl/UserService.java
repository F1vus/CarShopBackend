package edu.team.carshopbackend.service.impl;

import edu.team.carshopbackend.entity.Profile;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.entity.impl.UserDetailsImpl;
import edu.team.carshopbackend.repository.ProfileRepository;
import edu.team.carshopbackend.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User %s not found", email)
        ));
        return UserDetailsImpl.build(user);
    }

    @Transactional
    public void register(final User user) throws EntityExistsException {
        if(userRepository.existsUserByEmail(user.getEmail())){
            throw new EntityExistsException("Email already exists");
        }

        User savedUser = userRepository.save(user);
        Profile profile = new Profile();
        profile.setUser(savedUser);
        profileRepository.save(profile);
    }
}
