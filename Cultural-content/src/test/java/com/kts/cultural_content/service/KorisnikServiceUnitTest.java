package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.VerificationToken;
import com.kts.cultural_content.repository.AdminRepository;
import com.kts.cultural_content.repository.KorisnikRepository;
import com.kts.cultural_content.repository.VerificationTokenRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.kts.cultural_content.constants.KorisnikConstants.*;
import static com.kts.cultural_content.constants.KorisnikConstants.ADMIN_ID;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KorisnikServiceUnitTest {

    @Autowired
    private KorisnikService korisnikService;

    @MockBean
    private KorisnikRepository korisnikRepository;

    @MockBean
    private VerificationTokenRepository vtRepository;


    @Before
    public void setup() {
        Korisnik kCreate=new Korisnik(9,"Srbislav","Vucenovic","Srbislav1","srbislav@gmail.com","admin");
        Korisnik kExists=new Korisnik(3,"Aca","Acic","acika","3@gmail.com","user");
        given(korisnikRepository.findByKorisnickoIme("Srbislav1")).willReturn(null);
        given(korisnikRepository.save(kCreate)).willReturn(kCreate);
        given(korisnikRepository.findByKorisnickoIme("acika")).willReturn(kExists);
        given(korisnikRepository.getOne(3)).willReturn(kExists);
        given(korisnikRepository.save(kExists)).willReturn(kExists);
       // given(vtRepository.save(any(VerificationToken.class)).willReturn(null);
    }

    @Test
    public void testCreate() throws Exception {
        //Admin admin=new Admin(NEW_USER_ID,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_USERNAME,NEW_USER_EMAIL,NEW_USER_PASSWORD);
        Korisnik kCreate=new Korisnik(9,"Srbislav","Vucenovic","Srbislav1","srbislav@gmail.com","admin");

        Korisnik created=korisnikService.create(kCreate);
        //verify(korisnikRepository, times(1)).findByKorisnickoIme("Srbislav1");
        verify(korisnikRepository, times(1)).save(kCreate);

        assertEquals("Srbislav1", created.getKorisnickoIme());
    }
    /*
    @Test//(expected = Exception.class)
    public void testCreateUserNameExists() throws Exception {
         try {
            Korisnik created = korisnikService.create(kExists);
        }catch (Exception ex){
            //assertEquals("Korisnik with given username already exists",ex.getMessage());
        }
        verify(korisnikRepository, times(1)).findByKorisnickoIme("acika");

    }

     */
    @Test
    public void testDelete() throws Exception {
        korisnikService.delete(3);
        verify(korisnikRepository, times(1)).getOne(3);
    }
    //NE TREBA
    /*
    @Test
    public void testCreateVerificationToken(){
        Korisnik k=new Korisnik("3gmail.com","user");

        korisnikService.createVerificationToken((RegistrovaniKorisnik) k,"token123");
        //verify(vtRepository,times(1)).save(any(VerificationToken.class));
    }

     */
}
