package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Novost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.kts.cultural_content.constants.NovostConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class NovostRepositoryIntegrationTest {

    @Autowired
    private NovostRepository novostRepository;

    @Test
    public void testFindByNaziv() {
        Novost found = novostRepository.findByNaziv(DB_NOVOST_NAZIV);
        assertEquals(DB_NOVOST_NAZIV, found.getNaziv());
    }

    @Test
    public void testFindByNazivAndIdNot() {
        Novost found = novostRepository.findByNazivAndIdNot(DB_NOVOST_NAZIV, DB_NOVOST_ID);
        assertNull(found);
    }

}
