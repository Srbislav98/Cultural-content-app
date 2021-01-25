package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Recenzija;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static com.kts.cultural_content.constants.RecenzijaConstants.*;
import static com.kts.cultural_content.constants.RecenzijaConstants.VEC_KREIRANA_Recenzija;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RecenzijaServiceIntegrationTest {
    @Autowired
    private RecenzijaService RecenzijaService;

    @Test
    public void testFindAll(){
        List<Recenzija> found = RecenzijaService.findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFindAllPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE, PAGEABLE_SIZE);
        Page<Recenzija> found = RecenzijaService.findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }

    @Test
    public void testFindById() {
        Recenzija found = RecenzijaService.findOne(100);
        assertEquals(VEC_KREIRANA_Recenzija, found.getId());
    }

    @Test
    public void testCreate() throws Exception {
        Recenzija Recenzija = new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO);
        Recenzija created = RecenzijaService.create(Recenzija);

        assertEquals(NEW_OCENA_DOBRO, created.getOcena());
        assertEquals(new File(NEW_Fotografija_DOBRO), created.getFoto());
        assertEquals(NEW_Komentar_DOBRO, created.getKomentar());
    }

    @Test
    public void testUpdate() throws Exception {
        Recenzija Recenzija = new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO);
        Recenzija.setRegistrovaniKorisnik(new RegistrovaniKorisnik());
        Recenzija created = RecenzijaService.update(Recenzija,VEC_KREIRANA_Recenzija2);

        assertEquals(NEW_OCENA_DOBRO, created.getOcena());
        assertEquals(new File(NEW_Fotografija_DOBRO), created.getFoto());
        assertEquals(NEW_Komentar_DOBRO, created.getKomentar());
    }

    @Test
    public void testDelete() throws Exception {
        RecenzijaService.delete(VEC_KREIRANA_Recenzija);
        assertNull(RecenzijaService.findOne(VEC_KREIRANA_Recenzija));

        Recenzija savedRecenzija = new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO);
        savedRecenzija.setId(VEC_KREIRANA_Recenzija);
        RecenzijaService.create(savedRecenzija);
    }
}
