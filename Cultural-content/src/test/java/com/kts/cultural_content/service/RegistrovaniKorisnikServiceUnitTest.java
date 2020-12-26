package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.AdminRepository;
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

import java.util.ArrayList;
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
    private AdminRepository adminRepository;
    @MockBean
    private KulturnaPonudaRepository kpRepository;
    @Before
    public void setup() {
        RegistrovaniKorisnik userCreate=new RegistrovaniKorisnik(92,"David","Vucenovic","David123","david@gmail.com","user");
        RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        Korisnik usrCreate=new Korisnik(92,"David","Vucenovic","David123","david@gmail.com","user");
        Korisnik usrExists=new Korisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());
        KulturnaPonuda prvaKp=new KulturnaPonuda();
        prvaKp.setId(100);
        KulturnaPonuda drugaKp=new KulturnaPonuda();
        drugaKp.setId(101);
        ArrayList<RegistrovaniKorisnik> korisnici=new ArrayList<RegistrovaniKorisnik>();
        RegistrovaniKorisnik prviKorisnik=new RegistrovaniKorisnik();
        prviKorisnik.setKorisnickoIme("arak");
        prviKorisnik.setId(1);
        prviKorisnik.setEmail("123gmail.com");
        korisnici.add(prviKorisnik);
        RegistrovaniKorisnik drugiKorisnik=new RegistrovaniKorisnik();
        drugiKorisnik.setKorisnickoIme("mark");
        drugiKorisnik.setId(4);
        drugiKorisnik.setEmail("5gmail.com");
        korisnici.add(drugiKorisnik);
        RegistrovaniKorisnik treciKorisnik=new RegistrovaniKorisnik();
        treciKorisnik.setKorisnickoIme("mark210");
        treciKorisnik.setId(210);
        treciKorisnik.setEmail("210gmail.com");
        korisnici.add(treciKorisnik);
        ArrayList<Admin> admini=new ArrayList<Admin>();
        Admin prviAdmin=new Admin();
        prviAdmin.setKorisnickoIme("alak");
        prviAdmin.setId(2);
        prviAdmin.setEmail("124gmail.com");
        admini.add(prviAdmin);

        given(rkRepository.findAll()).willReturn(korisnici);
        given(adminRepository.findAll()).willReturn(admini);
        given(rkRepository.findById(5)).willReturn(Optional.ofNullable(null));
        given(rkRepository.findById(4)).willReturn(Optional.ofNullable(drugiKorisnik));
        given(rkRepository.findById(3)).willReturn(Optional.ofNullable(null));
        given(rkRepository.findById(2)).willReturn(Optional.ofNullable(null));
        given(adminRepository.findById(5)).willReturn(Optional.ofNullable(null));
        given(adminRepository.findById(4)).willReturn(Optional.ofNullable(null));
        given(adminRepository.findById(3)).willReturn(Optional.ofNullable(null));
        given(adminRepository.findById(2)).willReturn(Optional.ofNullable(prviAdmin));
        given(adminRepository.findById(1)).willReturn(Optional.ofNullable(null));

        given(rkRepository.findByKorisnickoIme("David123")).willReturn(null);
        given(rkRepository.save(userCreate)).willReturn(userCreate);
        given(rkRepository.findByKorisnickoIme("arak")).willReturn(userExists);
        given(adminRepository.findByKorisnickoIme("arak")).willReturn(null);
        given(rkRepository.findByEmail("123@gmail.com")).willReturn(userExists);
        given(adminRepository.findByEmail("123@gmail.com")).willReturn(null);
        given(rkRepository.findById(1)).willReturn(Optional.ofNullable(userExists));
        given(rkRepository.findById(92)).willReturn(Optional.ofNullable(null));
        given(adminRepository.findById(92)).willReturn(Optional.ofNullable(null));
        given(rkRepository.findByKorisnickoImeAndIdNot("arak",1)).willReturn(null);
        given(rkRepository.save(userExists)).willReturn(userExists);

        given(kpRepository.findById(100)).willReturn(Optional.ofNullable(prvaKp));
        given(kpRepository.findById(101)).willReturn(Optional.ofNullable(drugaKp));

    }
    @Test
    public void testCreatewithRK() throws Exception {
        RegistrovaniKorisnik userCreate=new RegistrovaniKorisnik(92,"David","Vucenovic","David123","david@gmail.com","user");
        RegistrovaniKorisnik created=rkService.create(userCreate);
        verify(rkRepository, times(1)).findByKorisnickoIme("David123");
        verify(rkRepository, times(1)).save(userCreate);

        assertEquals("David123", created.getKorisnickoIme());
    }
    @Test
    public void testCreatewithRKEmailExists() throws Exception {
        RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
      userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());
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
       Korisnik usrCreate=new Korisnik(92,"David","Vucenovic","David123","david@gmail.com","user");

        RegistrovaniKorisnik created=rkService.create(usrCreate);
        verify(rkRepository, times(1)).findByKorisnickoIme("David123");
        //verify(rkRepository, times(1)).save(userCreate);

        //assertEquals("David123", created.getKorisnickoIme());
    }
    @Test
    public void testCreateUsernameExists() throws Exception {
        Korisnik usrExists=new Korisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());

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
      RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());
        RegistrovaniKorisnik created=rkService.update(userExists,1);
        verify(rkRepository, times(1)).findById(1);
        //verify(rkRepository, times(1)).findByKorisnickoImeAndIdNot("arak",1);

        assertEquals("arak", created.getKorisnickoIme());
    }
    @Test(expected = Exception.class)
    public void testUpdateIdDoesntExists() throws Exception {
        RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());
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
        RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());

        rkService.UpdateResetPassword("axyz","123@gmail.com");
        verify(rkRepository,times(1)).save(userExists);
    }
    @Test(expected = KorisnikNotFoundException.class)
    public void testUpdateResetPasswordKorisnikNotFound() throws KorisnikNotFoundException {
        RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());

        rkService.UpdateResetPassword("axyz","2143@gmail.com");
        verify(rkRepository,times(0)).save(userExists);

    }
    @Test
    public void testUpdatePasword() throws InterruptedException {
        RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());

        rkService.updatePassword(userExists,"admin");
        verify(rkRepository,times(1)).save(userExists);
    }
    @Test
    public void testSubscribe() throws InterruptedException {
        RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());

        rkService.subscribe(1,101);
        verify(kpRepository,times(1)).findById(101);
        verify(rkRepository,times(1)).save(userExists);
    }
    @Test
    public void testUnsubscribe() throws InterruptedException {
        RegistrovaniKorisnik userExists=new RegistrovaniKorisnik(1,"Aca","Acic","arak","123@gmail.com","user");
        userExists.setKulturnaPonuda(new HashSet<KulturnaPonuda>());

        rkService.unsubscribe(1,100);
        verify(kpRepository,times(1)).findById(100);
        verify(rkRepository,times(1)).save(userExists);
    }
}
