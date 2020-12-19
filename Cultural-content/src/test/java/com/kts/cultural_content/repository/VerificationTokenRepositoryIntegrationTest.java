package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Uloga;
import com.kts.cultural_content.model.VerificationToken;
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
public class VerificationTokenRepositoryIntegrationTest {

    @Autowired
    private VerificationTokenRepository vTokenRepository;

    @Test
    public void testFindByToken(){
        VerificationToken found=vTokenRepository.findByToken("abb");
        assertEquals("abb",found.getToken());
    }
    @Test
    public void testFindByTokenNull(){
        VerificationToken found=vTokenRepository.findByToken("abcc");
        assertNull(found);
    }
}
