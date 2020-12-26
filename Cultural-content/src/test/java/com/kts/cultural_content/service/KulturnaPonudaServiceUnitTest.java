package com.kts.cultural_content.service;


import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Lokacija;
import com.kts.cultural_content.model.TipKulturnePonude;
import com.kts.cultural_content.repository.AdminRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.LokacijaRepository;
import com.kts.cultural_content.repository.TipKPRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.kts.cultural_content.constants.KorisnikConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import org.springframework.security.test.context.support.WithMockUser;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KulturnaPonudaServiceUnitTest {
    @MockBean
    private KulturnaPonudaRepository kulturnaPonudaRepository;
    @Autowired
    private KulturnaPonudaService kulturnaPonudaService;

    int NEW_KULTURNAPONUDA_ID = 150;
    String NEW_KULTURNAPONUDA_NAME = "Unit test KP";
    String NEW_KULTURNAPONUDA_ADDRESS = "Jelene Savic 52";
    String NEW_KULTURNAPONUDA_ABOUT = "KP za testiranje unit testova";
    TipKulturnePonude NEW_KULTURNAPONUDA_TYPE = new TipKulturnePonude(100, "obicna");
    Admin NEW_KULTURNAPONUDA_ADMIN = new Admin(2, "ime", "Prezime", "124@gmail.com", "123456789", null);
    Lokacija NEW_KULTURNAPONUDA_LOCATION = new Lokacija(100, "sadasf", 456, 165);

    int KULTURNAPONUDA_ID = 100;
    String KULTURNAPONUDA_NAME = "kulturnaponuda";
    String KULTURNAPONUDA_ADDRESS = "test";
    String KULTURNAPONUDA_ABOUT = "ggez";
    TipKulturnePonude KULTURNAPONUDA_TYPE = new TipKulturnePonude(100, "obicna");
    Admin KULTURNAPONUDA_ADMIN = new Admin(2, "ime", "Prezime", "124@gmail.com", "123456789", null);
    Lokacija KULTURNAPONUDA_LOCATION = new Lokacija(100, "sadasf", 456, 165);

    int FIND_ALL_NUMBER_OF_ITEMS = 3;
    int PAGEABLE_PAGE = 1;
    int PAGEABLE_SIZE = 2;
    int CATEGORY_ID = 100;
    String NEW_CATEGORY = "TestNaziv";

    @Before
    public void setup() {
        KulturnaPonuda KPCreate=new KulturnaPonuda(NEW_KULTURNAPONUDA_ID,
                NEW_KULTURNAPONUDA_NAME, NEW_KULTURNAPONUDA_ADDRESS, NEW_KULTURNAPONUDA_ABOUT,
                NEW_KULTURNAPONUDA_TYPE, NEW_KULTURNAPONUDA_ADMIN, NEW_KULTURNAPONUDA_LOCATION);
        KulturnaPonuda KPExists=new KulturnaPonuda(KULTURNAPONUDA_ID,
                KULTURNAPONUDA_NAME, KULTURNAPONUDA_ADDRESS, KULTURNAPONUDA_ABOUT,
                KULTURNAPONUDA_TYPE, KULTURNAPONUDA_ADMIN, KULTURNAPONUDA_LOCATION);

        given(kulturnaPonudaRepository.findByNaziv(NEW_KULTURNAPONUDA_NAME)).willReturn(null);
        given(kulturnaPonudaRepository.save(KPCreate)).willReturn(KPCreate);
        List<KulturnaPonuda> li = new ArrayList<>();
        li.add(0, KPExists);
        given(kulturnaPonudaRepository.findByAdmin(KULTURNAPONUDA_ADMIN)).willReturn(li );
        given(kulturnaPonudaRepository.findById(KULTURNAPONUDA_ID)).willReturn(Optional.ofNullable(KPExists));
        given(kulturnaPonudaRepository.findByLokacija(KULTURNAPONUDA_LOCATION)).willReturn(li);
        given(kulturnaPonudaRepository.save(KPExists)).willReturn(KPExists);
    }

    @Test
    public void testFindAll() {
        List<KulturnaPonuda> found = kulturnaPonudaService.findAll();

        verify(kulturnaPonudaRepository, times(1)).findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFindAllPageable() {
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE,PAGEABLE_SIZE);
        Page<KulturnaPonuda> found = kulturnaPonudaService.findAll(pageable);

        verify(kulturnaPonudaRepository, times(1)).findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }

    @Test
    public void testFindById() {
        KulturnaPonuda found = kulturnaPonudaService.findOne(KULTURNAPONUDA_ID);

        verify(kulturnaPonudaRepository, times(1)).findById(CATEGORY_ID);
        assertEquals((int)KULTURNAPONUDA_ID, (int)found.getId());
    }

    @Test
    //@WithMockUser(username = "admin", authorities = { "ADMIN", "USER" })
    public void testCreate() throws Exception {
        KulturnaPonuda kulturnaPonuda = new KulturnaPonuda(NEW_KULTURNAPONUDA_ID,
                NEW_KULTURNAPONUDA_NAME, NEW_KULTURNAPONUDA_ADDRESS, NEW_KULTURNAPONUDA_ABOUT,
                NEW_KULTURNAPONUDA_TYPE, NEW_KULTURNAPONUDA_ADMIN, NEW_KULTURNAPONUDA_LOCATION);
        KulturnaPonuda created = kulturnaPonudaService.create(kulturnaPonuda);

        verify(kulturnaPonudaRepository, times(1)).findByNaziv(NEW_KULTURNAPONUDA_NAME);
        verify(kulturnaPonudaRepository, times(1)).save(kulturnaPonuda);

        assertEquals(NEW_KULTURNAPONUDA_NAME, created.getNaziv());
    }

/*
    @Test
    public void testCreate_GivenNameAlreadyExists() throws Exception {
        KulturnaPonuda KulturnaPonuda = new KulturnaPonuda(DB_CATEGORY);
        KulturnaPonuda created = KulturnaPonudaService.create(KulturnaPonuda);

        verify(kulturnaPonudaRepository, times(1)).findByName(DB_CATEGORY);

        assertEquals(null, created);
    }

    @Test
    public void testUpdate() throws Exception {
        KulturnaPonuda KulturnaPonuda = new KulturnaPonuda(NEW_CATEGORY);
        KulturnaPonuda created = KulturnaPonudaService.update(KulturnaPonuda,CATEGORY_ID );

        verify(KulturnaPonudaRepository, times(1)).findById(CATEGORY_ID);
        verify(KulturnaPonudaRepository, times(1)).findByNameAndIdNot(NEW_CATEGORY,CATEGORY_ID);

        assertEquals(NEW_CATEGORY, created.getName());
    }

    @Test
    public void testDelete() throws Exception {
        KulturnaPonudaService.delete(CATEGORY_ID);

        KulturnaPonuda savedKulturnaPonuda = new KulturnaPonuda(NEW_CATEGORY);
        savedKulturnaPonuda.setId(CATEGORY_ID);

        verify(KulturnaPonudaRepository, times(1)).findById(CATEGORY_ID);
    }*/
}
