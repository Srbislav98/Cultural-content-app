package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Lokacija;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.Assert.*;
import static com.kts.cultural_content.constants.KPFiltersConstants.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class KPFilterRepositoryIntegrationTest {

    @Autowired
    private KulturnaPonudaRepository kulturnaPonudaRepository;

    @Autowired LokacijaRepository lokacijaRepository;

    @Test
    public void testFindByLokacija() {
        Lokacija lokacija = lokacijaRepository.findByNazivLokacije("Novi Sad");
        List<KulturnaPonuda> kulturnaPonudas = kulturnaPonudaRepository.findByLokacija(lokacija);
        assertEquals(FIND_ALL_FILTERED_BY_LOCATION_NUMBER_OF_ITEMS, kulturnaPonudas.size());
    }

    @Test
    public void testFindDistinctByNazivContainingOrOpisContainingOrderByNaziv() {
        List<KulturnaPonuda> kulturnaPonudas = kulturnaPonudaRepository.findDistinctByNazivContainingOrOpisContainingOrderByNaziv("g", "g");
        assertEquals(FIND_ALL_FILTERED_BY_CONTENT_NUMBER_OF_ITEMS, kulturnaPonudas.size());
    }

}
