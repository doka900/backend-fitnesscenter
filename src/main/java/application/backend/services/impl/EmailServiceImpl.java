package application.backend.services.impl;

import application.backend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendVerificationEmail(String email, String verificationUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email Verifikacija");
        message.setText("Pritisnite sledeći email da potvrdite registraciju: " + verificationUrl);
        javaMailSender.send(message);
    }
}
