package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CustomUserDetailsServiceIntegrationTest {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RegistrovaniKorisnikService rkService;

    @Test
    public void testLoadUserByUsername(){
        UserDetails user = customUserDetailsService.loadUserByUsername("3@gmail.com");
        assertEquals("acika",user.getUsername());
    }
    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNull(){
        UserDetails user = customUserDetailsService.loadUserByUsername("33@gmail.com");
        assertNull(user);
    }
    @Test
    @Transactional
    @Rollback
    public void testChangePassword() throws InterruptedException {
        Date sad=new Date();
        Thread.sleep(1000);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("5@gmail.com",
                        "user"));

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);
        customUserDetailsService.changePassword("user","korisnik");
        RegistrovaniKorisnik rk= rkService.findByEmail("5@gmail.com");
        assertTrue(rk.getLastPasswordResetDate().after(sad));
    }
    @Test(expected = BadCredentialsException.class)
    //@Transactional
    //@Rollback
    public void testChangePasswordWrong(){
        Date sad=new Date();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("5@gmail.com",
                        "user"));

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);
        customUserDetailsService.changePassword("usr","korisnik");
        RegistrovaniKorisnik rk= rkService.findByEmail("5@gmail.com");
        assertTrue(rk.getLastPasswordResetDate().after(sad));
    }
}
