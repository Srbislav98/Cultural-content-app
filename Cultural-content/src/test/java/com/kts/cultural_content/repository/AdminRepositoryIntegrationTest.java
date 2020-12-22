package com.kts.cultural_content.repository;
import com.kts.cultural_content.model.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class AdminRepositoryIntegrationTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void testFindByKorisnickoIme(){
        Admin found=adminRepository.findByKorisnickoIme("alak");
        assertEquals("alak", found.getKorisnickoIme());
    }
    @Test
    public void testFindByKorisnickoImeNull(){
        Admin found=adminRepository.findByKorisnickoIme("alk");
        assertNull(found);
    }
    @Test
    public void testFindByKorisnickoImeAndIdNot(){
        Admin found= adminRepository.findByKorisnickoImeAndIdNot("alak",6);
        assertEquals("alak", found.getKorisnickoIme());
    }
    @Test
    public void testFindByKorisnickoImeAndIdNotNull(){
        Admin found= adminRepository.findByKorisnickoImeAndIdNot("alk",6);
        assertNull(found);
    }
    @Test
    public void testfindByEmail(){
        Admin found=adminRepository.findByEmail("124@gmail.com");
        assertEquals("124@gmail.com", found.getEmail());
    }
    @Test
    public void testfindByEmailNull(){
        Admin found=adminRepository.findByEmail("125@gmail.com");
        assertNull(found);
    }

}
