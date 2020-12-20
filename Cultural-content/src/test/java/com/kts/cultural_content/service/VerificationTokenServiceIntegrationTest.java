package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Uloga;
import com.kts.cultural_content.model.VerificationToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class VerificationTokenServiceIntegrationTest {
    @Autowired
    VerificationTokenService verificationTokenService;

    @Test
    public void testFindByToken() {
        VerificationToken found = verificationTokenService.findByToken("abb");
        assertEquals("abb",found.getToken().toString());
    }
    @Test
    public void testFindByTokenNull() {
        VerificationToken found = verificationTokenService.findByToken("cba");
        assertNull(found);
    }
}
