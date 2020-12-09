package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.*;
import com.kts.cultural_content.mapper.KorisnikMapper;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.VerificationToken;
import com.kts.cultural_content.security.TokenUtils;
import com.kts.cultural_content.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private KorisnikService userService;
    @Autowired
    private RegistrovaniKorisnikService rkService;

    @Autowired
    private VerificationTokenService vtService;

    @Autowired
    EmailService emailService;

    private KorisnikMapper userMapper;


    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping("/log-in")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
                                                       HttpServletResponse response) {

        /*RegistrovaniKorisnik k=rkService.findByEmail(authenticationRequest.getUsername());
        if(k!=null) {
            System.out.println(k.getEnabled());
            if (!k.isEnabled()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }*/

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /*
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        Korisnik u=(Korisnik)auth.getPrincipal();
        */

        // Kreiraj token za tog korisnika
        Korisnik user = (Korisnik) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail()); // prijavljujemo se na sistem sa email adresom
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
    }

    // Endpoint za registraciju novog korisnika
    @PostMapping("/sign-up")
    public ResponseEntity<?> addUser(@RequestBody KorisnikDTO userRequest) throws Exception {

        RegistrovaniKorisnik existUser = this.rkService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            throw new Exception("Username already exists");
        }
        existUser = this.rkService.findByKorisnickoIme(userRequest.getKorisnickoIme());
        if (existUser != null) {
            throw new Exception("Username already exists");
        }

        try {
            //RegistrovaniKorisnikDTO a=new RegistrovaniKorisnikDTO(userRequest);
            existUser = rkService.create(userMapper.toEntity(userRequest));
            emailService.confirmRegistration(existUser);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userMapper.toDto(existUser), HttpStatus.CREATED);
    }

    // U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token osvezi
    @PostMapping(value = "/refresh")
    public ResponseEntity<UserTokenStateDTO> refreshAuthenticationToken(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        Korisnik user = (Korisnik) this.userDetailsService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenStateDTO(refreshedToken, expiresIn));
        } else {
            UserTokenStateDTO userTokenState = new UserTokenStateDTO();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }
    @GetMapping("/regitrationConfirm/{token}")
    public ResponseEntity<?> confirmRegistration(@PathVariable("token") String url) throws Exception {
        VerificationToken verificationToken = vtService.findByToken(url);
        if (verificationToken != null) {
            RegistrovaniKorisnik user = verificationToken.getUser();
            user.setEnabled(true);
            //userService.delete(user.getId());
            rkService.save(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }

    public AuthenticationController() {
        userMapper = new KorisnikMapper();
    }
}

