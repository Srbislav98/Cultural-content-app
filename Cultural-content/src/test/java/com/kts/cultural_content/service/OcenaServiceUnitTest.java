package com.kts.cultural_content.service;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.OcenaRepository;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
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

import java.util.ArrayList;
import java.util.List;

import static com.kts.cultural_content.constants.OcenaConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class OcenaServiceUnitTest {

    @Autowired
    private OcenaService ocenaService;

    @MockBean
    private OcenaRepository oRepository;
    @MockBean
    private KulturnaPonudaRepository kulturnaPonudaRepository;
    @MockBean
    private RegistrovaniKorisnikRepository registrovaniKorisnikRepository;

    @Before
    public void setup(){
        List<Ocena> ocenas = new ArrayList<>();
        ocenas.add(new Ocena(NEW_OCENA_DOBRO,1,100));
        ocenas.add(new Ocena(4,1,100));

        Pageable pageable = PageRequest.of(PAGEABLE_PAGE,PAGEABLE_SIZE);
        Page<Ocena> ocenaPage = new PageImpl<>(ocenas, pageable, PAGEABLE_TOTAL_ELEMENTS);

        given(oRepository.findAll()).willReturn(ocenas);

        given(oRepository.findAll(pageable)).willReturn(ocenaPage);

        Ocena ocena = new Ocena(NEW_OCENA_DOBRO,1,100);
        Ocena savedOcena = new Ocena(NEW_OCENA_DOBRO,1,100);
        savedOcena.setId(VEC_KREIRANA_OCENA);

        given(oRepository.findById(VEC_KREIRANA_OCENA)).willReturn(java.util.Optional.of(savedOcena));

        Ocena ocenaFound = new Ocena(NEW_OCENA_DOBRO, 1,100);
        ocenaFound.setId(VEC_KREIRANA_OCENA2);
        given(oRepository.findById(VEC_KREIRANA_OCENA2)).willReturn(java.util.Optional.of(ocenaFound));

        KulturnaPonuda kulturnaPonuda = new KulturnaPonuda();
        kulturnaPonuda.setId(100);
        RegistrovaniKorisnik registrovaniKorisnik = new RegistrovaniKorisnik();
        registrovaniKorisnik.setId(1);

        given(kulturnaPonudaRepository.findById(100)).willReturn(java.util.Optional.of(kulturnaPonuda));
        given(registrovaniKorisnikRepository.findById(1)).willReturn(java.util.Optional.of(registrovaniKorisnik));
        ocena.setRegistrovaniKorisnik(registrovaniKorisnik);
        ocena.setKulturnaPonuda(kulturnaPonuda);
        given(oRepository.save(ocena)).willReturn(savedOcena);

        doNothing().when(oRepository).delete(savedOcena);



    }

    @Test
    public void testFindAll(){
        List<Ocena> found = ocenaService.findAll();

        verify(oRepository, times(1)).findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFindAllPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE,PAGEABLE_SIZE);
        Page<Ocena> found = ocenaService.findAll(pageable);

        verify(oRepository, times(1)).findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }

    @Test
    public void testFindById(){
        Ocena found = ocenaService.findOne(VEC_KREIRANA_OCENA);

        verify(oRepository, times(1)).findById(VEC_KREIRANA_OCENA);
        assertEquals(VEC_KREIRANA_OCENA, found.getId());
    }

    @Test
    public void testCreate() throws Exception {
        Ocena ocena = new Ocena(NEW_OCENA_DOBRO,1,100);
        Ocena created = ocenaService.create(ocena);

        //verify(oRepository, times(1)).findById(VEC_KREIRANA_OCENA);
        verify(kulturnaPonudaRepository, times(1)).findById(100);
        verify(registrovaniKorisnikRepository, times(1)).findById(1);
        verify(oRepository, times(1)).save(ocena);

        assertEquals(NEW_OCENA_DOBRO, created.getVrednost());
    }

    @Test
    public void testCreateNedozvoljenBroj() throws Exception {
        Ocena ocena = new Ocena(NEW_OCENA_LOSE,1,100);
        Ocena created = ocenaService.create(ocena);


        assertEquals(null, created);
    }

    @Test
    public void testUpdate() throws Exception {
        Ocena ocena = new Ocena(NEW_OCENA_DOBRO,1,100);
        Ocena created = ocenaService.update(ocena,VEC_KREIRANA_OCENA );

        verify(kulturnaPonudaRepository, times(1)).findById(100);
        verify(registrovaniKorisnikRepository, times(1)).findById(1);
        verify(oRepository, times(1)).save(ocena);

        assertEquals(NEW_OCENA_DOBRO, created.getVrednost());
    }

    @Test
    public void testDelete() throws Exception {
        ocenaService.delete(VEC_KREIRANA_OCENA);

        Ocena savedOcena = new Ocena(NEW_OCENA_DOBRO,1,100);
        savedOcena.setId(VEC_KREIRANA_OCENA);

        verify(oRepository, times(1)).findById(VEC_KREIRANA_OCENA);
    }

}
