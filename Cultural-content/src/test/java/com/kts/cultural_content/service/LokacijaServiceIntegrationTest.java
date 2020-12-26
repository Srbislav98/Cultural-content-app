package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Lokacija;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.kts.cultural_content.constants.LokacijaConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class LokacijaServiceIntegrationTest {

    @Autowired
    private LokacijaService lokacijaService;

    @Test
    public void testFindOne() {
        Lokacija lokacija = lokacijaService.findOne(DB_LOKACIJA_ID);
        assertEquals(DB_LOKACIJA_ID, lokacija.getId());
    }

    @Test
    public void testFindAll() {
        List<Lokacija> lokacije = lokacijaService.findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, lokacije.size());
    }

    @Test
    public void testFindByNazivLokacije() {
        Lokacija lokacija = lokacijaService.findByNazivLokacije(DB_LOKACIJA_NAZIV);
        assertEquals(DB_LOKACIJA_NAZIV, lokacija.getNazivLokacije());
    }

    @Test
    public void testFindByNazivLokacijeFail() {
        Lokacija lokacija = lokacijaService.findByNazivLokacije("greska");
        assertNull(lokacija);
    }

    @Test
    public void testFindOneFail() {
        Lokacija lokacija = lokacijaService.findOne(9999);
        assertNull(lokacija);
    }

}
