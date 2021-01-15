package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.AdminDTO;
import com.kts.cultural_content.dto.EmailHelperDTO;
import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.Utility;
import com.kts.cultural_content.service.KorisnikNotFoundException;
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

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/recover", produces = MediaType.APPLICATION_JSON_VALUE)
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RegistrovaniKorisnikService oKorisnik;

    @RequestMapping(value="/password", method= RequestMethod.POST)
    public ResponseEntity<String> ProcesForgotPassword(@RequestBody EmailHelperDTO passw) throws KorisnikNotFoundException {
        String token = RandomString.make(45);
        String email= passw.getEmail();
        System.out.println("Email " + email);
        System.out.println("Token " + token);

        if (oKorisnik.findByEmail(email) == null)
            return new ResponseEntity<String>("Email not found", HttpStatus.NOT_FOUND);
        //System.out.println("Email " + email);
        oKorisnik.UpdateResetPassword(token, email);
        RegistrovaniKorisnik kor = oKorisnik.findByEmail(email);
        //System.out.println("Email " + email);
        //generisanje linka
        //String resetPasswordLink = "http://localhost:8080/recover" + "/reset_password/" + token; //Utility.getSiteURL();
        //slanje mejla
        String recipientAddress = email;
        String subject = "Recover Password";
        String message = "Thanks for using Cultural Content app,your new password is ";
        ///System.out.println("Email " + email);
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String newPass = salt.toString();
        //System.out.println("Email " + email);
        SimpleMailMessage semail = new SimpleMailMessage();
        //recipientAddress, ali za provjeru za sada moj mail
        semail.setTo(recipientAddress);
        semail.setSubject(subject);
        semail.setText(message + newPass/*"\r\n" + resetPasswordLink*/);
        mailSender.send(semail);
        oKorisnik.updatePassword(kor, newPass);
        System.out.println("Emaildsds " + email);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }
    /*
    @RequestMapping(value="/reset_password/{token}", method= RequestMethod.POST)
    public String ProcesResetPassword(@RequestBody String newPassword, @PathVariable String token){

        System.out.println("Token : " + token);
        System.out.println("New Password : " + newPassword);

        RegistrovaniKorisnik kor = oKorisnik.get(token);

        if(kor == null)
            return "Invalid Token";
        else{
            oKorisnik.updatePassword(kor, newPassword);
            return "Password changed successfully";
        }
    }
    */
}
