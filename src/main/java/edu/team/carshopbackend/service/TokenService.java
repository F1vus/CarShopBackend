package edu.team.carshopbackend.service;

import edu.team.carshopbackend.entity.EmailVerificationToken;
import edu.team.carshopbackend.entity.User;
import edu.team.carshopbackend.error.exception.NotFoundException;
import edu.team.carshopbackend.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    @Transactional
    public EmailVerificationToken getToken(String token, User user) {
        return tokenRepository
                .findByUserAndToken(user, token)
                .orElseThrow(() -> new NotFoundException("Invalid code"));
    }

    public EmailVerificationToken createToken(User user) {
        EmailVerificationToken token =
                tokenRepository.findByUser(user).orElse(new EmailVerificationToken());

        token.setUser(user);
        token.setToken(generateToken());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10));

        return tokenRepository.save(token);
    }

    public void deleteToken(EmailVerificationToken token) {
        tokenRepository.deleteById(token.getId());
    }

    private String generateToken() {
        return String.format("%06d", ThreadLocalRandom.current().nextInt(0, 1_000_000));
    }
}
