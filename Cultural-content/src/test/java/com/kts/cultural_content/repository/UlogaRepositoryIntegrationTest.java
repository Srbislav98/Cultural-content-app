package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.Uloga;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class UlogaRepositoryIntegrationTest {

    @Autowired
    private UlogaRepository ulogaRepository;

    @Test
    public void testFindByIme(){
        List<Uloga> found=ulogaRepository.findByIme("ROLE_ADMIN");
        assertEquals(1, found.size());
        assertEquals("ROLE_ADMIN",found.get(0).getIme());
    }
    @Test
    public void testFindByImeNull(){
        List<Uloga> found=ulogaRepository.findByIme("ROLE_PRODAVAC");
        assertEquals(0,found.size());
    }
}
