package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.Korisnik;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class KorisnikRepositoryIntegrationTest {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Test
    public void testFindByKorisnickoIme(){
        Korisnik found=korisnikRepository.findByKorisnickoIme("acika");
        assertEquals("acika", found.getKorisnickoIme());
    }
    @Test
    public void testFindByKorisnickoImeNull(){
        Korisnik found=korisnikRepository.findByKorisnickoIme("acka");
        assertNull(found);
    }
    @Test
    public void testfindByEmail(){
        Korisnik found=korisnikRepository.findByEmail("3@gmail.com");
        assertEquals("3@gmail.com", found.getEmail());
    }
    @Test
    public void testfindByEmailNull(){
        Korisnik found=korisnikRepository.findByEmail("4@gmail.com");
        assertNull(found);
    }
}
