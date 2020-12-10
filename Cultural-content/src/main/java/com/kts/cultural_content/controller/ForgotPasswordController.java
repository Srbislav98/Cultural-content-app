package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.Utility;
import com.kts.cultural_content.service.KulturnaPonudaService;
import com.kts.cultural_content.service.RegistrovaniKorisnikService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/recover", produces = MediaType.APPLICATION_JSON_VALUE)
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RegistrovaniKorisnikService oKorisnik;

    @RequestMapping(value="/password", method= RequestMethod.POST)
    public String ProcesForgotPassword(@RequestBody String email){
        String token = RandomString.make(45);

        System.out.println("Email " + email);
        System.out.println("Token " + token);

        oKorisnik.updatePassword(oKorisnik.findByEmail(email), token);
        RegistrovaniKorisnik kor = oKorisnik.findByEmail(email);

        //generisanje linka
        String resetPasswordLink = "http://localhost:8080/api/recover" + "/reset_password?token=" + token; //Utility.getSiteURL();
        //slanje mejla
        String recipientAddress = email;
        String subject = "Recover Password";
        String message = "Thanks for using Cultural Content app,here is your activation link.";

        SimpleMailMessage semail = new SimpleMailMessage();
        //recipientAddress, ali za provjeru za sada moj mail
        semail.setTo(recipientAddress);
        semail.setSubject(subject);
        semail.setText(message + "\r\n" + resetPasswordLink);
        mailSender.send(semail);

        return token;
    }

    @RequestMapping(value="/reset_password", method= RequestMethod.POST)
    public String ProcesResetPassword(@RequestBody String token, String newPassword){
        RegistrovaniKorisnik kor = oKorisnik.get(token);

        if(kor == null)
            return "Invalid Token";
        else{
            oKorisnik.updatePassword(kor, newPassword);
            return "Password changed successfully";
        }
    }
}
