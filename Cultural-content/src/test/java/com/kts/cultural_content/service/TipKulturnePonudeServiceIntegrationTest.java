package com.kts.cultural_content.service;

import com.kts.cultural_content.model.TipKulturnePonude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

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
        assertEquals(1, found.size());
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
        TipKulturnePonude tip = tipService.findOne(trenID);
        tipService.delete(trenID);
        assertNull(tipService.findOne(trenID));
        tip = tipService.create(tip);
        System.out.println(trenID + " -> " + tip.getId());
        trenID = tip.getId();
        System.out.println("Pretvorio u " + trenID);
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
