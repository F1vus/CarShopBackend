package edu.team.carshopbackend.repository;


import edu.team.carshopbackend.entity.EmailVerificationToken;
import edu.team.carshopbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<EmailVerificationToken, Long> {
    Optional<EmailVerificationToken> findByUser(User user);

    Optional<EmailVerificationToken> findByUserAndToken(User user, String token);
}
