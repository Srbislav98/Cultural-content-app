package com.kts.cultural_content.service;

import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.Uloga;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class UlogaServiceIntegrationTest {

    @Autowired UlogaService ulogaService;

    @Test
    public void testFindByName() {
        List<Uloga> found = ulogaService.findByname("ROLE_USER");
        assertEquals(1,found.size());
    }
    @Test
    public void testFindByNameNull() {
        List<Uloga> found = ulogaService.findByname("ROLE_USERR");
        assertEquals(0,found.size());
    }

    @Test
    @Transactional
    public void testFindById() {
        List<Uloga> found = ulogaService.findById(1);
        assertEquals(1,found.size());
        assertEquals("ROLE_ADMIN",found.get(0).getIme());
    }
}
