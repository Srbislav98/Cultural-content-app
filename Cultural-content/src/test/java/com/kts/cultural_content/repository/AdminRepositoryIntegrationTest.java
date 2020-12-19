package com.kts.cultural_content.repository;
import com.kts.cultural_content.model.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

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
    public void testFindByKorisnickoImeAndIdNot(){

    }
    @Test
    public void testfindByEmail(){

    }

}
