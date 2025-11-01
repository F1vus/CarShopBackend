package edu.team.carshopbackend.config.jwtConfig;

import edu.team.carshopbackend.entity.impl.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtCore {
    private final int lifetime;

    private final SecretKey secretKey;

    public JwtCore(@Value("${jwt.secret-base64}") final String secret, @Value("${jwt.lifetime}") final int lifetime) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.lifetime = lifetime;
    }

    public String generateToken(final Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + lifetime))
                .signWith(secretKey)
                .compact();
    }

    public Claims extractAllClaims(final String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getNameFromToken(final String token) {
        System.out.println();
        return extractAllClaims(token).getSubject();
    }
}
