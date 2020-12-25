package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Fotografija;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.kts.cultural_content.constants.FotografijaConstants.*;
import static com.kts.cultural_content.constants.FotografijaConstants.VEC_KREIRANA_Fotografija;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class FotografijaServiceIntegrationTest {
    @Autowired
    private FotografijaService fotografijaService;

    @Test
    public void testFindAll(){
        List<Fotografija> found = fotografijaService.findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    /*@Test
    public void testFindAllPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE, PAGEABLE_SIZE);
        Page<Fotografija> found = fotografijaService.findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }*/

    @Test
    public void testFindById() {
        Fotografija found = fotografijaService.findOne(100);
        assertEquals(VEC_KREIRANA_Fotografija, found.getId());
    }

    @Test
    public void testCreate() throws Exception {
        Fotografija Fotografija = new Fotografija(NEW_Fotografija_DOBRO,1,100);
        Fotografija created = fotografijaService.create(Fotografija);

        assertEquals(NEW_Fotografija_DOBRO, created.getLokacijaFajl());
    }

    @Test
    public void testUpdate() throws Exception {
        Fotografija Fotografija = new Fotografija(NEW_Fotografija_DOBRO,1,100);
        Fotografija created = fotografijaService.update(Fotografija,VEC_KREIRANA_Fotografija2);

        assertEquals(NEW_Fotografija_DOBRO, created.getLokacijaFajl());
    }

    /*@Test
    public void testCreateLose() throws Exception {
        Fotografija Fotografija = new Fotografija(NEW_Fotografija_LOSE,1,100);
        Fotografija created = fotografijaService.create(Fotografija);

        assertEquals(null, created);
    }

    @Test
    public void testUpdateLose() throws Exception {
        Fotografija Fotografija = new Fotografija(NEW_Fotografija_LOSE,1,100);
        Fotografija created = fotografijaService.update(Fotografija,VEC_KREIRANA_Fotografija2);

        assertEquals(null, created);
    }*/

    @Test
    public void testDelete() throws Exception {
        fotografijaService.delete(VEC_KREIRANA_Fotografija);
        assertNull(fotografijaService.findOne(VEC_KREIRANA_Fotografija));

        Fotografija savedFotografija = new Fotografija(NEW_Fotografija_DOBRO,1,100);
        savedFotografija.setId(VEC_KREIRANA_Fotografija);
        fotografijaService.create(savedFotografija);
    }
}
