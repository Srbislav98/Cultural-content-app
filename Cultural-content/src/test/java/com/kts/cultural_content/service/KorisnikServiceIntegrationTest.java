package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.VerificationToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KorisnikServiceIntegrationTest {

    @Autowired
    KorisnikService korisnikService;

    @Autowired
    RegistrovaniKorisnikService rkService;

    @Autowired
    VerificationTokenService vtService;

    @Test
    public void testFindAll() {
        List<Korisnik> found = korisnikService.findAll();
        for (Korisnik a:found
        ) {
            System.out.println(a.getId() + "/////////////////////////////////////////////");
        }
        assertEquals(5, found.size());
    }

    @Test
    public void testFindOne() {
        Korisnik found = korisnikService.findOne(3);
        Long l = 3L;
        assertEquals(l, Long.valueOf(found.getId()));
    }

    @Test
    public void testCreate() throws Exception {
        Korisnik k = new Korisnik(17, "Marko", "Markovic", "Markec", "marko@gmail.com", "mark12");
        Korisnik created = korisnikService.create(k);
        assertEquals(k.getEmail(), created.getEmail());
        korisnikService.delete(k.getId());

    }


    @Test
    public void testDelete() throws Exception {
        //da sacuvamo korisnika da bi ga kasnije vratili
        Korisnik k = korisnikService.findOne(3);
        korisnikService.delete(3);
        assertNull(korisnikService.findOne(3));
        //ako prodje sve znaci da je ok, vracamo admina
        korisnikService.create(k);

    }
    @Test
    public void testFindByEmail() {
        Korisnik found = korisnikService.findByEmail("3@gmail.com");
        assertEquals("3@gmail.com",found.getEmail());
    }
    @Test
    public void testFindByEmailNull() {
        Korisnik found = korisnikService.findByEmail("33@gmail.com");
        assertNull(found);
    }
    @Test
    public void testFindByKorisnickoIme() {
        Korisnik found = korisnikService.findByKorisnickoIme("acika");
        assertEquals("acika",found.getKorisnickoIme());
    }
    @Test
    public void testFindByKorisnickoImeNull() {
        Korisnik found = korisnikService.findByKorisnickoIme("amika");
        assertNull(found);
    }
    @Test
    public void testCreateVerificationToken() {
        RegistrovaniKorisnik found = rkService.findByKorisnickoIme("mark");
        korisnikService.createVerificationToken(found,"abcsa");
        VerificationToken token=vtService.findByToken("abcsa");
        assertEquals(token.getUser().getEmail(),found.getEmail());
    }

    public void testSave(){
        Korisnik found = korisnikService.findByKorisnickoIme("acika");
        found.setIme("Miodragov");
        korisnikService.save(found);
        Korisnik foundAgain = korisnikService.findByKorisnickoIme("acika");
        assertEquals("Miodragov",foundAgain.getIme());


    }

}
