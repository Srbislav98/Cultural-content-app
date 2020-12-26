package com.kts.cultural_content.repository;
import com.kts.cultural_content.model.TipKulturnePonude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class TipKulturnePonudeRepositoryIntegrationTest {
    @Autowired
    private TipKPRepository oTip;

    @Test
    public void findByNazivTest(){
        TipKulturnePonude found= oTip.findByNaziv("obicna").get(0);
        assertEquals("obicna", found.getNaziv());
    }
}
