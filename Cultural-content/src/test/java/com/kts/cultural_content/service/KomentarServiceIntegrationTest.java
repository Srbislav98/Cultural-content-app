package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Komentar;
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

import static com.kts.cultural_content.constants.KomentarConstants.*;
import static com.kts.cultural_content.constants.KomentarConstants.VEC_KREIRANA_Komentar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KomentarServiceIntegrationTest {
    @Autowired
    private KomentarService komentarService;

    @Test
    public void testFindAll(){
        List<Komentar> found = komentarService.findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFindAllPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE, PAGEABLE_SIZE);
        Page<Komentar> found = komentarService.findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }

    @Test
    public void testFindById() {
        Komentar found = komentarService.findOne(100);
        assertEquals(VEC_KREIRANA_Komentar, found.getId());
    }

    @Test
    public void testCreate() throws Exception {
        Komentar Komentar = new Komentar(NEW_Komentar_DOBRO,1,100);
        Komentar created = komentarService.create(Komentar);

        assertEquals(NEW_Komentar_DOBRO, created.getVrednost());
    }

    @Test
    public void testUpdate() throws Exception {
        Komentar Komentar = new Komentar(NEW_Komentar_DOBRO,1,100);
        Komentar created = komentarService.update(Komentar,VEC_KREIRANA_Komentar2);

        assertEquals(NEW_Komentar_DOBRO, created.getVrednost());
    }

    /*@Test
    public void testCreateLose() throws Exception {
        Komentar Komentar = new Komentar(NEW_Komentar_LOSE,1,100);
        Komentar created = komentarService.create(Komentar);

        assertEquals(null, created);
    }

    @Test
    public void testUpdateLose() throws Exception {
        Komentar Komentar = new Komentar(NEW_Komentar_LOSE,1,100);
        Komentar created = komentarService.update(Komentar,VEC_KREIRANA_Komentar2);

        assertEquals(null, created);
    }*/

    @Test
    public void testDelete() throws Exception {
        komentarService.delete(VEC_KREIRANA_Komentar);
        assertNull(komentarService.findOne(VEC_KREIRANA_Komentar));

        Komentar savedKomentar = new Komentar(NEW_Komentar_DOBRO,1,100);
        savedKomentar.setId(VEC_KREIRANA_Komentar);
        komentarService.create(savedKomentar);
    }
}
