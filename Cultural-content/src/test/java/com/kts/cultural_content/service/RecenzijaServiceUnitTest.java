package com.kts.cultural_content.service;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Recenzija;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.RecenzijaRepository;
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

import static com.kts.cultural_content.constants.RecenzijaConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RecenzijaServiceUnitTest {
    @Autowired
    private RecenzijaService RecenzijaService;

    @MockBean
    private com.kts.cultural_content.repository.RecenzijaRepository RecenzijaRepository;
    @MockBean
    private KulturnaPonudaRepository kulturnaPonudaRepository;
    @MockBean
    private RegistrovaniKorisnikRepository registrovaniKorisnikRepository;

    @Before
    public void setup(){
        List<Recenzija> Recenzijas = new ArrayList<>();
        Recenzijas.add(new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO));
        Recenzijas.add(new Recenzija(null,3, "NEW_Komentar_DOBRO", 1,100, NEW_Fotografija_DOBRO));

        Pageable pageable = PageRequest.of(PAGEABLE_PAGE,PAGEABLE_SIZE);
        Page<Recenzija> RecenzijaPage = new PageImpl<>(Recenzijas, pageable, PAGEABLE_TOTAL_ELEMENTS);

        given(RecenzijaRepository.findAll()).willReturn(Recenzijas);

        given(RecenzijaRepository.findAll(pageable)).willReturn(RecenzijaPage);

        Recenzija Recenzija = new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO);
        Recenzija savedRecenzija = new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO);
        savedRecenzija.setId(VEC_KREIRANA_Recenzija);

        given(RecenzijaRepository.findById(VEC_KREIRANA_Recenzija)).willReturn(java.util.Optional.of(savedRecenzija));

        Recenzija RecenzijaFound = new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO);
        RecenzijaFound.setId(VEC_KREIRANA_Recenzija2);
        given(RecenzijaRepository.findById(VEC_KREIRANA_Recenzija2)).willReturn(java.util.Optional.of(RecenzijaFound));

        KulturnaPonuda kulturnaPonuda = new KulturnaPonuda();
        kulturnaPonuda.setId(100);
        RegistrovaniKorisnik registrovaniKorisnik = new RegistrovaniKorisnik();
        registrovaniKorisnik.setId(1);

        given(kulturnaPonudaRepository.findById(100)).willReturn(java.util.Optional.of(kulturnaPonuda));
        given(registrovaniKorisnikRepository.findById(1)).willReturn(java.util.Optional.of(registrovaniKorisnik));
        Recenzija.setRegistrovaniKorisnik(registrovaniKorisnik);
        Recenzija.setKulturnaPonuda(kulturnaPonuda);
        given(RecenzijaRepository.save(Recenzija)).willReturn(savedRecenzija);

        doNothing().when(RecenzijaRepository).delete(savedRecenzija);



    }

    @Test
    public void testFindAll(){
        List<Recenzija> found = RecenzijaService.findAll();

        verify(RecenzijaRepository, times(1)).findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    @Test
    public void testFindAllPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE,PAGEABLE_SIZE);
        Page<Recenzija> found = RecenzijaService.findAll(pageable);

        verify(RecenzijaRepository, times(1)).findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }

    @Test
    public void testFindById(){
        Recenzija found = RecenzijaService.findOne(VEC_KREIRANA_Recenzija);

        verify(RecenzijaRepository, times(1)).findById(VEC_KREIRANA_Recenzija);
        assertEquals(VEC_KREIRANA_Recenzija, found.getId());
    }

    @Test
    public void testCreate() throws Exception {
        Recenzija Recenzija = new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO);
        Recenzija created = RecenzijaService.create(Recenzija);

        //verify(oRepository, times(1)).findById(VEC_KREIRANA_Recenzija);
        verify(kulturnaPonudaRepository, times(1)).findById(100);
        verify(registrovaniKorisnikRepository, times(1)).findById(1);
        verify(RecenzijaRepository, times(1)).save(Recenzija);

        assertEquals(NEW_OCENA_DOBRO, created.getOcena());
        assertEquals(NEW_Fotografija_DOBRO, created.getFotoLokacija());
        assertEquals(NEW_Komentar_DOBRO, created.getKomentar());
    }

    /*@Test
    public void testCreateNedozvoljenBroj() throws Exception {
        Recenzija Recenzija = new Recenzija(NEW_Recenzija_LOSE,1,100);
        Recenzija created = RecenzijaService.create(Recenzija);


        assertEquals(null, created);
    }*/

    @Test
    public void testUpdate() throws Exception {
        Recenzija Recenzija = new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO);
        Recenzija.setRegistrovaniKorisnik(new RegistrovaniKorisnik());
        Recenzija created = RecenzijaService.update(Recenzija,VEC_KREIRANA_Recenzija );

        verify(kulturnaPonudaRepository, times(1)).findById(100);
        verify(registrovaniKorisnikRepository, times(1)).findById(1);
        verify(RecenzijaRepository, times(1)).save(Recenzija);

        assertEquals(NEW_OCENA_DOBRO, created.getOcena());
        assertEquals(NEW_Fotografija_DOBRO, created.getFotoLokacija());
        assertEquals(NEW_Komentar_DOBRO, created.getKomentar());
    }

    @Test
    public void testDelete() throws Exception {
        RecenzijaService.delete(VEC_KREIRANA_Recenzija);

        Recenzija savedRecenzija = new Recenzija(null,NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, NEW_Fotografija_DOBRO);
        savedRecenzija.setId(VEC_KREIRANA_Recenzija);

        verify(RecenzijaRepository, times(1)).findById(VEC_KREIRANA_Recenzija);
    }
}
