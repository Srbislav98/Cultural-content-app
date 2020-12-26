package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Lokacija;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.kts.cultural_content.constants.LokacijaConstants.DB_LOKACIJA_NAZIV;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class LokacijaRepositoryIntegrationTest {

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Test
    public void testFindByNazivLokacije() {
        Lokacija lokacija = lokacijaRepository.findByNazivLokacije(DB_LOKACIJA_NAZIV);
        assertEquals(DB_LOKACIJA_NAZIV, lokacija.getNazivLokacije());
    }

}
