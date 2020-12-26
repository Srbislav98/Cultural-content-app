package com.kts.cultural_content.repository;
import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Lokacija;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class KulturnaPonudaRepositoryIntegrationTest {
    @Autowired
    private KulturnaPonudaRepository oKulturnaPonuda;

    @Autowired
    private AdminRepository oAdmin;

    @Autowired
    private LokacijaRepository oLokacija;

    @Test
    public void findByNazivTest(){
        KulturnaPonuda found= oKulturnaPonuda.findByNaziv("kulturnaponuda").get(0);
        assertEquals("kulturnaponuda", found.getNaziv());
    }

    @Test
    public void findByAdminTest(){
        Admin a = oAdmin.getOne(2);

        KulturnaPonuda found= oKulturnaPonuda.findByAdmin(a).get(0);
        assertEquals(a, found.getAdmin());
    }

    @Test
    public void findByLokacijaTest(){
        Lokacija lokacija = oLokacija.getOne(100);

        List<KulturnaPonuda> found= oKulturnaPonuda.findByLokacija(lokacija);
        assertEquals(4, found.size());
        assertEquals("kulturnaponuda", found.get(0).getNaziv());
        assertEquals("nekulturnaponuda", found.get(1).getNaziv());
        assertEquals("ponuda", found.get(2).getNaziv());
        assertEquals("glhf", found.get(3).getNaziv());
    }

}
