package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.VerificationToken;
import com.kts.cultural_content.repository.KorisnikRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

import static com.kts.cultural_content.constants.KorisnikConstants.NEW_ADMIN_USERNAME;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CustomUserDetailsServiceUnitTest {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    KorisnikRepository korisnikRepository;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    PasswordEncoder passwordEncoder;

    public Korisnik korisnik;
    public Authentication auth;
    public Korisnik korisnikb;
    public Authentication authb;
    @Before
    public void setup() {

        korisnik=new Korisnik("5@gmail.com","user");
        korisnikb=new Korisnik("5@gmail.com","usmer");
        //given(korisnikRepository.save(korisnik)).getMock();
        auth = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return korisnik;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
        authb = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return korisnikb;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
        given(authenticationManager.authenticate(auth)).willReturn(auth);
        given(authenticationManager.authenticate(authb)).willThrow(new BadCredentialsException("ASAFAFASS"));
        given(korisnikRepository.findByEmail("5@gmail.com")).willReturn(korisnik);
        given(passwordEncoder.encode("admin")).willReturn("$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK");
    }

    @Test
    @Transactional
    @Rollback
    public void testChangePassword() throws InterruptedException {
        Date sad=new Date();
        Thread.sleep(1000);

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(auth);
        customUserDetailsService.changePassword("user","admin");

        verify(korisnikRepository, times(1)).save(korisnik);
        verify(korisnikRepository, times(1)).findByEmail("5@gmail.com");
  }
  /*
    @Test(expected = BadCredentialsException.class)
    //@Transactional
    //@Rollback
    public void testChangePasswordWrong(){
        //Date sad=new Date();
        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authb);
        customUserDetailsService.changePassword("usmer","korisnik");
        //verify(korisnikRepository, times(0)).save(korisnik);
        //verify(korisnikRepository, times(0)).findByEmail("5@gmail.com");

    }

   */
}
