package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
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
public class RegistrovaniKorisnikRepositoryIntegrationTest {

    @Autowired
    private RegistrovaniKorisnikRepository rkRepository;

    @Test
    public void testFindByKorisnickoIme(){
        RegistrovaniKorisnik found=rkRepository.findByKorisnickoIme("mark");
        assertEquals("mark", found.getKorisnickoIme());
    }
    @Test
    public void testFindByKorisnickoImeNull(){
        RegistrovaniKorisnik found=rkRepository.findByKorisnickoIme("alk");
        assertNull(found);
    }
    @Test
    public void testFindByKorisnickoImeAndIdNot(){
        RegistrovaniKorisnik found= rkRepository.findByKorisnickoImeAndIdNot("mark",6);
        assertEquals("mark", found.getKorisnickoIme());
    }
    @Test
    public void testFindByKorisnickoImeAndIdNotNull(){
        RegistrovaniKorisnik found= rkRepository.findByKorisnickoImeAndIdNot("mark",1);
        assertNull(found);
    }
    @Test
    public void testfindByEmail(){
        RegistrovaniKorisnik found=rkRepository.findByEmail("4@gmail.com");
        assertEquals("4@gmail.com", found.getEmail());
    }
    @Test
    public void testfindByEmailNull(){
        RegistrovaniKorisnik found=rkRepository.findByEmail("125@gmail.com");
        assertNull(found);
    }

    @Test
    public void testfindByResetPasswordToken(){
        RegistrovaniKorisnik found=rkRepository.findByResetPasswordToken("abc");
        assertEquals("abc", found.getResetPasswordToken());
    }
    @Test
    public void testfindByResetPasswordTokenNull(){
        RegistrovaniKorisnik found=rkRepository.findByResetPasswordToken("abd");
        assertNull(found);
    }

}
