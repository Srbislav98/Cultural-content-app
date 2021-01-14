package com.kts.cultural_content.service;

import ch.qos.logback.core.encoder.EchoEncoder;
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

import javax.management.BadAttributeValueExpException;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private KorisnikService service;


    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void confirmRegistration(RegistrovaniKorisnik user) throws Exception {
        if(user.getEnabled()==true){
            throw new BadAttributeValueExpException("User has already confirmed registration");
        }
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl="/registrationConfirm/"+token;
        String message = "Thanks for using Cultural Content app,here is your activation link.";

        SimpleMailMessage email = new SimpleMailMessage();
        //recipientAddress, ali za provjeru za sada moj mail
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:4200" + confirmationUrl);
        mailSender.send(email);
    }
}