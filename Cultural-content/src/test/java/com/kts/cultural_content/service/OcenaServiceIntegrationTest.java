package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Ocena;
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

import static com.kts.cultural_content.constants.OcenaConstants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class OcenaServiceIntegrationTest {
    @Autowired
    private OcenaService ocenaService;

    @Test
    public void testFindAll(){
        List<Ocena> found = ocenaService.findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFindAllPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE, PAGEABLE_SIZE);
        Page<Ocena>found = ocenaService.findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }

    @Test
    public void testFindById() {
        Ocena found = ocenaService.findOne(100);
        assertEquals(VEC_KREIRANA_OCENA, found.getId());
    }

    @Test
    public void testCreate() throws Exception {
        Ocena ocena = new Ocena(NEW_OCENA_DOBRO,1,100);
        Ocena created = ocenaService.create(ocena);

        assertEquals(NEW_OCENA_DOBRO, created.getVrednost());
    }

    @Test
    public void testUpdate() throws Exception {
        Ocena ocena = new Ocena(NEW_OCENA_DOBRO,1,100);
        Ocena created = ocenaService.update(ocena,VEC_KREIRANA_OCENA2);

        assertEquals(NEW_OCENA_DOBRO, created.getVrednost());
    }

    @Test
    public void testCreateLose() throws Exception {
        Ocena ocena = new Ocena(NEW_OCENA_LOSE,1,100);
        Ocena created = ocenaService.create(ocena);

        assertEquals(null, created);
    }

    @Test
    public void testUpdateLose() throws Exception {
        Ocena ocena = new Ocena(NEW_OCENA_LOSE,1,100);
        Ocena created = ocenaService.update(ocena,VEC_KREIRANA_OCENA2);

        assertEquals(null, created);
    }

    @Test
    public void testDelete() throws Exception {
        ocenaService.delete(VEC_KREIRANA_OCENA);

        Ocena savedOcena = new Ocena(NEW_OCENA_DOBRO,1,100);
        savedOcena.setId(VEC_KREIRANA_OCENA);
        ocenaService.create(savedOcena);
    }


}
