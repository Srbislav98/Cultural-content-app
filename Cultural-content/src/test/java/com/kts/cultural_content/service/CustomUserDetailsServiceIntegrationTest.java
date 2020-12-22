package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Korisnik;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class CustomUserDetailsServiceIntegrationTest {

    @Autowired
    KorisnikService korisnikService;

    @Test
    public void testLoadUserByUsername(){
        Korisnik user = korisnikService.findByEmail("3@gmail.com");
        assertEquals("3@gmail.com",user.getEmail());
    }
    @Test
    public void testLoadUserByUsernameNull(){
        Korisnik user = korisnikService.findByEmail("33@gmail.com");
        assertNull(user);
    }
    @Test
    public void testChangePassword(){
    }
}
