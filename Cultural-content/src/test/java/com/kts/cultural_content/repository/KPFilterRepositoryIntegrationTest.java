package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Lokacija;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Test
    public void testFindDistinctByNazivContainingOrOpisContainingOrderByNazivPageable() {
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_0, PAGEABLE_SIZE);
        Page<KulturnaPonuda> kulturnaPonudas = kulturnaPonudaRepository.findDistinctByNazivContainingOrOpisContainingOrderByNaziv(pageable, "g", "g");
        assertEquals(FILTERED_BY_CONTENT_NUMBER_OF_ITEMS_PAGE_0, kulturnaPonudas.getNumberOfElements());
    }

    @Test
    public void testFindDistinctByNazivContainingOrOpisContainingOrderByNazivPageable2() {
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_1, PAGEABLE_SIZE);
        Page<KulturnaPonuda> kulturnaPonudas = kulturnaPonudaRepository.findDistinctByNazivContainingOrOpisContainingOrderByNaziv(pageable, "g", "g");
        assertEquals(FILTERED_BY_CONTENT_NUMBER_OF_ITEMS_PAGE_1, kulturnaPonudas.getNumberOfElements());
    }

    @Test
    public void testFindByLokacijaPageable() {
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_0, PAGEABLE_SIZE);
        Lokacija lokacija = lokacijaRepository.findByNazivLokacije("Novi Sad");
        Page<KulturnaPonuda> kulturnaPonudas = kulturnaPonudaRepository.findByLokacija(pageable, lokacija);
        assertEquals(FILTERED_BY_LOCATION_NUMBER_OF_ITEMS_PAGE_0, kulturnaPonudas.getNumberOfElements());
    }

    @Test
    public void testFindByLokacijaPageable2() {
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE_1, PAGEABLE_SIZE);
        Lokacija lokacija = lokacijaRepository.findByNazivLokacije("Novi Sad");
        Page<KulturnaPonuda> kulturnaPonudas = kulturnaPonudaRepository.findByLokacija(pageable, lokacija);
        assertEquals(FILTERED_BY_LOCATION_NUMBER_OF_ITEMS_PAGE_1, kulturnaPonudas.getNumberOfElements());
    }

}
