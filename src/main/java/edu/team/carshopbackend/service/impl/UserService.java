package edu.team.carshopbackend.service.impl;

import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.entity.impl.UserDetailsImpl;
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

    @Transactional
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User %s not found", username)
        ));
        return UserDetailsImpl.build(user);
    }

    @Transactional
    public void register(final User user) throws EntityExistsException {
        if(userRepository.existsUserByUsername(user.getUsername())){
            throw new EntityExistsException("Username already exists");
        }
        if(userRepository.existsUserByEmail(user.getEmail())){
            throw new EntityExistsException("Email already exists");
        }

        userRepository.save(user);
    }
}
