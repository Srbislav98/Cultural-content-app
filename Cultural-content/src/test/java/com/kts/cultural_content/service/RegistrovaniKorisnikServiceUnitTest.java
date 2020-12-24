package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
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

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static com.kts.cultural_content.constants.KorisnikConstants.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RegistrovaniKorisnikServiceUnitTest {

    @Autowired
    private RegistrovaniKorisnikService rkService;
    @MockBean
    private RegistrovaniKorisnikRepository rkRepository;
    @MockBean
    private KulturnaPonudaRepository kpRepository;

    private RegistrovaniKorisnik userCreate, userExists;
    private Korisnik usrCreate,usrExists;
    private KulturnaPonuda prvaKp,drugaKp;
    @Before
    public void setup() {
        userCreate=new RegistrovaniKorisnik(92,"David","Vucenovic","David123","david@gmail.com","user");
        userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        usrCreate=new Korisnik(92,"David","Vucenovic","David123","david@gmail.com","user");
        usrExists=new Korisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());
        prvaKp=new KulturnaPonuda();
        prvaKp.setId(100);
        drugaKp=new KulturnaPonuda();
        drugaKp.setId(101);
        given(rkRepository.findByKorisnickoIme("David123")).willReturn(null);
        given(rkRepository.save(userCreate)).willReturn(userCreate);
        given(rkRepository.findByKorisnickoIme("arak")).willReturn(userExists);
        given(rkRepository.findByEmail("123@gmail.com")).willReturn(userExists);
        given(rkRepository.findById(1)).willReturn(Optional.ofNullable(userExists));
        given(rkRepository.findById(92)).willReturn(null);
        given(rkRepository.findByKorisnickoImeAndIdNot("arak",1)).willReturn(null);
        given(rkRepository.save(userExists)).willReturn(userExists);

        given(kpRepository.findById(100)).willReturn(Optional.ofNullable(prvaKp));
        given(kpRepository.findById(101)).willReturn(Optional.ofNullable(drugaKp));

    }
    @Test
    public void testCreatewithRK() throws Exception {
        RegistrovaniKorisnik created=rkService.create(userCreate);
        verify(rkRepository, times(1)).findByKorisnickoIme("David123");
        verify(rkRepository, times(1)).save(userCreate);

        assertEquals("David123", created.getKorisnickoIme());
    }
    @Test
    public void testCreatewithRKEmailExists() throws Exception {
        try {
            RegistrovaniKorisnik created = rkService.create(userExists);
        }catch (Exception ex){
            assertEquals("Korisnik with given username already exists",ex.getMessage());
        }
        verify(rkRepository, times(1)).findByKorisnickoIme("arak");
        verify(rkRepository, times(0)).save(userExists);
    }
    @Test
    public void testCreate() throws Exception {
        RegistrovaniKorisnik created=rkService.create(usrCreate);
        verify(rkRepository, times(1)).findByKorisnickoIme("David123");
        //verify(rkRepository, times(1)).save(userCreate);

        //assertEquals("David123", created.getKorisnickoIme());
    }
    @Test
    public void testCreateUsernameExists() throws Exception {
        try {
            RegistrovaniKorisnik created = rkService.create(usrExists);
        }catch (Exception ex){
            assertEquals("Korisnik with given username already exists",ex.getMessage());
        }
        verify(rkRepository, times(1)).findByKorisnickoIme("arak");
        verify(rkRepository, times(0)).save(userExists);
    }
    @Test
    public void testUpdate() throws Exception {
        RegistrovaniKorisnik created=rkService.update(userExists,1);
        verify(rkRepository, times(1)).findById(1);
        //verify(rkRepository, times(1)).findByKorisnickoImeAndIdNot("arak",1);

        assertEquals("arak", created.getKorisnickoIme());
    }
    @Test(expected = Exception.class)
    public void testUpdateIdDoesntExists() throws Exception {
        RegistrovaniKorisnik created=rkService.update(userExists,92);
        verify(rkRepository, times(1)).findById(92);
        //verify(rkRepository, times(1)).findByKorisnickoImeAndIdNot("arak",1);

        //assertEquals("arak", created.getKorisnickoIme());
    }
    @Test
    public void testDelete() throws Exception {
        rkService.delete(1);
        verify(rkRepository, times(1)).findById(1);
    }
    @Test

    public void testUpdateResetPassword() throws KorisnikNotFoundException {
        rkService.UpdateResetPassword("axyz","123@gmail.com");
        verify(rkRepository,times(1)).save(userExists);
    }
    @Test(expected = KorisnikNotFoundException.class)
    public void testUpdateResetPasswordKorisnikNotFound() throws KorisnikNotFoundException {
        rkService.UpdateResetPassword("axyz","2143@gmail.com");
        verify(rkRepository,times(0)).save(userExists);

    }
    @Test
    public void testUpdatePasword() throws InterruptedException {
        rkService.updatePassword(userExists,"admin");
        verify(rkRepository,times(1)).save(userExists);
    }
    @Test
    public void testSubscribe() throws InterruptedException {
        rkService.subscribe(1,101);
        verify(kpRepository,times(1)).findById(101);
        verify(rkRepository,times(1)).save(userExists);
    }
    @Test
    public void testUnsubscribe() throws InterruptedException {
        rkService.unsubscribe(1,100);
        verify(kpRepository,times(1)).findById(100);
        verify(rkRepository,times(1)).save(userExists);
    }
}
