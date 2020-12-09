package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.OnRegistrationCompleteEvent;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private KorisnikService service;


    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void confirmRegistration(RegistrovaniKorisnik user) {
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl="/auth/regitrationConfirm/"+token;
        String message = "Thanks for using Cultural Content app,here is your activation link.";

        SimpleMailMessage email = new SimpleMailMessage();
        //recipientAddress, ali za provjeru za sada moj mail
        email.setTo("srbislav30111998@gmail.com");
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}