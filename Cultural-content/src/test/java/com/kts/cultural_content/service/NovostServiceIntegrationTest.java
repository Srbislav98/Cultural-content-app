package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Novost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.kts.cultural_content.constants.NovostConstants.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class NovostServiceIntegrationTest {

    @Autowired
    private NovostService novostService;

    private final SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testFindAll(){
        List<Novost> found = novostService.findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFindAllPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE, PAGEABLE_SIZE);
        Page<Novost> found = novostService.findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }

    @Test
    public void testFindById() {
        Novost found = novostService.findOne(VEC_KREIRANA_NOVOST);
        assertEquals(VEC_KREIRANA_NOVOST, found.getId());
    }

    @Test
    public void testFindByIdFail() {
        Novost found = novostService.findOne(9999);
        assertNull(found);
    }

    @Test
    public void testCreate() throws Exception {
        Novost novost = new Novost(3, "n3", "", objSDF.parse("2020-12-30"));
        Novost created = novostService.createSaKulturnomPonudom(novost, 100);

        assertEquals("n3", created.getNaziv());
    }

    @Test
    public void testUpdate() throws Exception {
        Novost novost = new Novost(VEC_KREIRANA_NOVOST2, "novostC", "opis5", objSDF.parse("2020-12-21"));
        Novost created = novostService.update(novost, VEC_KREIRANA_NOVOST2);

        assertEquals("novostC", created.getNaziv());
    }

    @Test(expected = java.lang.Exception.class)
    public void testCreateFail() throws Exception {
        Novost novost = new Novost(VEC_KREIRANA_NOVOST, "novost", "opis", objSDF.parse("2020-12-12"));
        Novost created = novostService.createSaKulturnomPonudom(novost, 100);
    }

    @Test(expected = java.lang.Exception.class)
    public void testUpdateFail() throws Exception {
        Novost novost = new Novost(VEC_KREIRANA_NOVOST, "novost", "opis", objSDF.parse("2020-12-12"));
        Novost created = novostService.update(novost,223);
    }

    @Test
    public void testDelete() throws Exception {
        novostService.delete(VEC_KREIRANA_NOVOST);
        assertNull(novostService.findOne(VEC_KREIRANA_NOVOST));

        Novost savedNovost = new Novost(100, "novost", "opis", objSDF.parse("2020-12-12"));
        savedNovost.setId(VEC_KREIRANA_NOVOST);
        novostService.createSaKulturnomPonudom(savedNovost, 100);
    }

}
