package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.TipKulturnePonude;
import com.kts.cultural_content.repository.TipKPRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.BadAttributeValueExpException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class TipKulturnePonudeServiceUnitTest {
    @Autowired
    private TipKPService tipKulturnePonudeService;

    @MockBean
    private TipKPRepository tipKulturnePonudeRepository;

    @Before
    public void setup() {
        TipKulturnePonude tipCreate=new TipKulturnePonude(666, "Ttest");
        TipKulturnePonude tipExsist=new TipKulturnePonude(100, "obicna");

        given(tipKulturnePonudeRepository.findByNaziv("Ttest")).willReturn(null);
        given(tipKulturnePonudeRepository.save(tipExsist)).willReturn(tipExsist);
        given(tipKulturnePonudeRepository.save(tipCreate)).willReturn(tipCreate);
    }

    @Test
    public void testCreatewithRK() throws Exception {
        TipKulturnePonude tipCreate=new TipKulturnePonude(666, "Ttest");
        TipKulturnePonude created=tipKulturnePonudeService.create(tipCreate);
        verify(tipKulturnePonudeRepository, times(1)).findByNaziv("Ttest");
        verify(tipKulturnePonudeRepository, times(1)).save(tipCreate);

        assertEquals("Ttest", created.getNaziv());
    }
    /*@Test
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
    }*/
}
