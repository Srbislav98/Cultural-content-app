package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.VerificationTokenRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import sun.plugin.util.ProgressMonitor;

import javax.management.BadAttributeValueExpException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class EmailServiceIntegrationTest {

    @Autowired
    EmailService emailService;

    @Autowired
    RegistrovaniKorisnikService rkService;

    @Autowired
    VerificationTokenRepository vtRepository;

    @Test
    public void testConfirmRegistration() throws Exception {
        RegistrovaniKorisnik korisnik= rkService.findByEmail("210@gmail.com");
        emailService.confirmRegistration(korisnik);
        assertNotNull(vtRepository.findByUserId(korisnik.getId()));
    }
    @Test(expected = BadAttributeValueExpException.class)
    public void testConfirmRegistrationAlreadyConfirmed() throws Exception {
        RegistrovaniKorisnik korisnik= rkService.findByEmail("123@gmail.com");
        emailService.confirmRegistration(korisnik);
    }
}
