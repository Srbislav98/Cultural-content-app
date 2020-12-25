package com.kts.cultural_content.service;

import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.VerificationTokenRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.BadAttributeValueExpException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class EmailServiceUnitTest {

    @Autowired
    EmailService emailService;

    @MockBean
    private JavaMailSender mailSender;

    @MockBean
    private KorisnikService service;

    @Before
    public void setup() {
        RegistrovaniKorisnik kConfirm=new RegistrovaniKorisnik();
        kConfirm.setEmail("210@gmail.com");
        kConfirm.setId(264);
        kConfirm.setKorisnickoIme("armasaak");
        kConfirm.setEnabled(false);
        RegistrovaniKorisnik kNConfirm=new RegistrovaniKorisnik();
        kNConfirm.setKorisnickoIme("arak");
        kNConfirm.setId(1);
        kNConfirm.setEnabled(true);
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));
        doNothing().when(service).createVerificationToken(any(RegistrovaniKorisnik.class),any(String.class));
    }

    @Test
    public void testConfirmRegistration() throws Exception {
        RegistrovaniKorisnik kConfirm=new RegistrovaniKorisnik();
        kConfirm.setEmail("210@gmail.com");
        kConfirm.setId(264);
        kConfirm.setKorisnickoIme("armasaak");
        kConfirm.setEnabled(false);
        emailService.confirmRegistration(kConfirm);
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
    @Test(expected = BadAttributeValueExpException.class)
    public void testConfirmRegistrationAlreadyConfirmed() throws Exception {
        RegistrovaniKorisnik kNConfirm=new RegistrovaniKorisnik();
        kNConfirm.setKorisnickoIme("arak");
        kNConfirm.setId(1);
        kNConfirm.setEnabled(true);
        emailService.confirmRegistration(kNConfirm);
        verify(mailSender, times(0)).send(any(SimpleMailMessage.class));
    }
}
