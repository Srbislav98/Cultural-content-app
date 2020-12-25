package com.kts.cultural_content.service;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.KomentarRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
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

import static com.kts.cultural_content.constants.KomentarConstants.*;
import static com.kts.cultural_content.constants.KomentarConstants.VEC_KREIRANA_Komentar;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KomentarServiceUnitTest {
    @Autowired
    private KomentarService komentarService;

    @MockBean
    private KomentarRepository komentarRepository;
    @MockBean
    private KulturnaPonudaRepository kulturnaPonudaRepository;
    @MockBean
    private RegistrovaniKorisnikRepository registrovaniKorisnikRepository;

    @Before
    public void setup(){
        List<Komentar> Komentars = new ArrayList<>();
        Komentars.add(new Komentar(NEW_Komentar_DOBRO,1,100));
        Komentars.add(new Komentar("wdwdwdw",1,100));

        Pageable pageable = PageRequest.of(PAGEABLE_PAGE,PAGEABLE_SIZE);
        Page<Komentar> KomentarPage = new PageImpl<>(Komentars, pageable, PAGEABLE_TOTAL_ELEMENTS);

        given(komentarRepository.findAll()).willReturn(Komentars);

        given(komentarRepository.findAll(pageable)).willReturn(KomentarPage);

        Komentar Komentar = new Komentar(NEW_Komentar_DOBRO,1,100);
        Komentar savedKomentar = new Komentar(NEW_Komentar_DOBRO,1,100);
        savedKomentar.setId(VEC_KREIRANA_Komentar);

        given(komentarRepository.findById(VEC_KREIRANA_Komentar)).willReturn(java.util.Optional.of(savedKomentar));

        Komentar KomentarFound = new Komentar(NEW_Komentar_DOBRO, 1,100);
        KomentarFound.setId(VEC_KREIRANA_Komentar2);
        given(komentarRepository.findById(VEC_KREIRANA_Komentar2)).willReturn(java.util.Optional.of(KomentarFound));

        KulturnaPonuda kulturnaPonuda = new KulturnaPonuda();
        kulturnaPonuda.setId(100);
        RegistrovaniKorisnik registrovaniKorisnik = new RegistrovaniKorisnik();
        registrovaniKorisnik.setId(1);

        given(kulturnaPonudaRepository.findById(100)).willReturn(java.util.Optional.of(kulturnaPonuda));
        given(registrovaniKorisnikRepository.findById(1)).willReturn(java.util.Optional.of(registrovaniKorisnik));
        Komentar.setRegistrovaniKorisnik(registrovaniKorisnik);
        Komentar.setKulturnaPonuda(kulturnaPonuda);
        given(komentarRepository.save(Komentar)).willReturn(savedKomentar);

        doNothing().when(komentarRepository).delete(savedKomentar);



    }

    @Test
    public void testFindAll(){
        List<Komentar> found = komentarService.findAll();

        verify(komentarRepository, times(1)).findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFindAllPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE,PAGEABLE_SIZE);
        Page<Komentar> found = komentarService.findAll(pageable);

        verify(komentarRepository, times(1)).findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }

    @Test
    public void testFindById(){
        Komentar found = komentarService.findOne(VEC_KREIRANA_Komentar);

        verify(komentarRepository, times(1)).findById(VEC_KREIRANA_Komentar);
        assertEquals(VEC_KREIRANA_Komentar, found.getId());
    }

    @Test
    public void testCreate() throws Exception {
        Komentar Komentar = new Komentar(NEW_Komentar_DOBRO,1,100);
        Komentar created = komentarService.create(Komentar);

        //verify(oRepository, times(1)).findById(VEC_KREIRANA_Komentar);
        verify(kulturnaPonudaRepository, times(1)).findById(100);
        verify(registrovaniKorisnikRepository, times(1)).findById(1);
        verify(komentarRepository, times(1)).save(Komentar);

        assertEquals(NEW_Komentar_DOBRO, created.getVrednost());
    }

    /*@Test
    public void testCreateNedozvoljenBroj() throws Exception {
        Komentar Komentar = new Komentar(NEW_Komentar_LOSE,1,100);
        Komentar created = komentarService.create(Komentar);


        assertEquals(null, created);
    }*/

    @Test
    public void testUpdate() throws Exception {
        Komentar Komentar = new Komentar(NEW_Komentar_DOBRO,1,100);
        Komentar created = komentarService.update(Komentar,VEC_KREIRANA_Komentar );

        verify(kulturnaPonudaRepository, times(1)).findById(100);
        verify(registrovaniKorisnikRepository, times(1)).findById(1);
        verify(komentarRepository, times(1)).save(Komentar);

        assertEquals(NEW_Komentar_DOBRO, created.getVrednost());
    }

    @Test
    public void testDelete() throws Exception {
        komentarService.delete(VEC_KREIRANA_Komentar);

        Komentar savedKomentar = new Komentar(NEW_Komentar_DOBRO,1,100);
        savedKomentar.setId(VEC_KREIRANA_Komentar);

        verify(komentarRepository, times(1)).findById(VEC_KREIRANA_Komentar);
    }
}
