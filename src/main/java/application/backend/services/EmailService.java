package application.backend.services;

public interface EmailService {

    public void sendVerificationEmail(String email, String verificationUrl);

}
