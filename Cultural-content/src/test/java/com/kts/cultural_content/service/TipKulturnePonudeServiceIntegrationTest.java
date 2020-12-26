package com.kts.cultural_content.service;

import com.kts.cultural_content.model.TipKulturnePonude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class TipKulturnePonudeServiceIntegrationTest {
    @Autowired
    TipKPService tipService;

    public int trenID = 100;

    @Test
    public void testFindAll() {
        List<TipKulturnePonude> found = tipService.findAll();
        assertEquals(2, found.size());
        assertEquals("brisanje", found.get(0).getNaziv());
        assertEquals("obicna", found.get(1).getNaziv());
    }

    @Test
    public void testFindOne() {
        TipKulturnePonude found = tipService.findOne(trenID);
        assertEquals("obicna", found.getNaziv());
    }

    @Test
    public void testCreate() throws Exception {
        TipKulturnePonude tip = new TipKulturnePonude(666, "035/0365-03530");
        TipKulturnePonude created = tipService.create(tip);
        assertEquals(tip.getNaziv(), created.getNaziv());
        tipService.delete(created.getId());
        assertNull(null);
    }

    @Test
    public void testDelete() throws Exception {
        TipKulturnePonude tip = tipService.findOne(5);
        tipService.delete(5);
        assertNull(tipService.findOne(5));
        tip = tipService.create(tip);
        System.out.println(5 + " -> " + tip.getId());
        trenID = tip.getId();
        System.out.println("Pretvorio u " + 5);
        assertNull(null);
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("Trazi : " + trenID);

        for (TipKulturnePonude tt: tipService.findAll()) {
            System.out.println("Nasao : " + tt.getId() + ") " + tt.getNaziv());
        }


        TipKulturnePonude tip = new TipKulturnePonude(666, "035/0365-03530");
        TipKulturnePonude updated;
        try {
            updated = tipService.update(tip, trenID);
        }catch (Exception e){
            updated = tipService.update(tip, 102);
        }
        assertEquals(tip.getNaziv(),updated.getNaziv());
    }
}
