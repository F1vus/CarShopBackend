package edu.team.carshopbackend.service;

import edu.team.carshopbackend.entity.EmailVerificationToken;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendVerificationEmail(String email, EmailVerificationToken token) {
        System.out.println("Send token: "+token.getToken()+"On email: " + email);
        //TODO
    }
}
