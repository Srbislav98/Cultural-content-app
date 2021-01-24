package com.kts.cultural_content.service;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.model.Recenzija;
import com.kts.cultural_content.repository.FotografijaRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.RecenzijaRepository;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.kts.cultural_content.constants.FotografijaConstants.*;
import static com.kts.cultural_content.constants.FotografijaConstants.VEC_KREIRANA_Fotografija;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class FotografijaServiceUnitTest {
    @Autowired
    private FotografijaService fotografijaService;

    @MockBean
    private FotografijaRepository fotografijaRepository;
    @MockBean
    private KulturnaPonudaRepository kulturnaPonudaRepository;
    @MockBean
    private RecenzijaRepository recenzijaRepository;

    @Before
    public void setup(){
        List<Fotografija> Fotografijas = new ArrayList<>();
        Fotografijas.add(new Fotografija(0,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100));
        Fotografijas.add(new Fotografija(0,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100));

        Pageable pageable = PageRequest.of(PAGEABLE_PAGE,PAGEABLE_SIZE);
        Page<Fotografija> FotografijaPage = new PageImpl<>(Fotografijas, pageable, PAGEABLE_TOTAL_ELEMENTS);

        given(fotografijaRepository.findAll()).willReturn(Fotografijas);

        given(fotografijaRepository.findAll(pageable)).willReturn(FotografijaPage);

        Fotografija Fotografija = new Fotografija(0,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100);
        Fotografija savedFotografija = new Fotografija(0,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100);
        savedFotografija.setId(VEC_KREIRANA_Fotografija);

        given(fotografijaRepository.findById(VEC_KREIRANA_Fotografija)).willReturn(java.util.Optional.of(savedFotografija));

        Fotografija FotografijaFound = new Fotografija(0,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100);
        FotografijaFound.setId(VEC_KREIRANA_Fotografija2);
        given(fotografijaRepository.findById(VEC_KREIRANA_Fotografija2)).willReturn(java.util.Optional.of(FotografijaFound));

        KulturnaPonuda kulturnaPonuda = new KulturnaPonuda();
        kulturnaPonuda.setId(100);
        Recenzija registrovaniKorisnik = new Recenzija();
        registrovaniKorisnik.setId(1);

        given(kulturnaPonudaRepository.findById(100)).willReturn(java.util.Optional.of(kulturnaPonuda));
        given(recenzijaRepository.findById(1)).willReturn(java.util.Optional.of(registrovaniKorisnik));
        Fotografija.setRecenzija(registrovaniKorisnik);
        Fotografija.setKulturnaPonuda(kulturnaPonuda);
        given(fotografijaRepository.save(Fotografija)).willReturn(savedFotografija);

        doNothing().when(fotografijaRepository).delete(savedFotografija);



    }

    @Test
    public void testFindAll(){
        List<Fotografija> found = fotografijaService.findAll();

        verify(fotografijaRepository, times(1)).findAll();
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.size());
    }

    /*@Test
    public void testFindAllPageable(){
        Pageable pageable = PageRequest.of(PAGEABLE_PAGE,PAGEABLE_SIZE);
        Page<Fotografija> found = fotografijaService.findAll(pageable);

        verify(fotografijaRepository, times(1)).findAll(pageable);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, found.getNumberOfElements());
    }*/

    @Test
    public void testFindById(){
        Fotografija found = fotografijaService.findOne(VEC_KREIRANA_Fotografija);

        verify(fotografijaRepository, times(1)).findById(VEC_KREIRANA_Fotografija);
        assertEquals(VEC_KREIRANA_Fotografija, found.getId());
    }

    @Test
    public void testCreate() throws Exception {
        Fotografija Fotografija = new Fotografija(0,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100);
        Fotografija created = fotografijaService.create(Fotografija);

        //verify(oRepository, times(1)).findById(VEC_KREIRANA_Fotografija);
        verify(kulturnaPonudaRepository, times(1)).findById(100);
        verify(recenzijaRepository, times(1)).findById(100);
        verify(fotografijaRepository, times(1)).save(Fotografija);

        assertEquals(NEW_Fotografija_DOBRO, created.getLokacijaFajl());
    }

    /*@Test
    public void testCreateNedozvoljenBroj() throws Exception {
        Fotografija Fotografija = new Fotografija(NEW_Fotografija_LOSE,1,100);
        Fotografija created = fotografijaService.create(Fotografija);


        assertEquals(null, created);
    }*/

    @Test
    public void testUpdate() throws Exception {
        Fotografija Fotografija = new Fotografija(0,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100);
        Fotografija created = fotografijaService.update(Fotografija,VEC_KREIRANA_Fotografija );


        verify(kulturnaPonudaRepository, times(1)).findById(100);
        verify(recenzijaRepository, times(1)).findById(100);
        verify(fotografijaRepository, times(1)).save(Fotografija);

        assertEquals(NEW_Fotografija_DOBRO, created.getLokacijaFajl());
    }

    /*@Test
    public void testUpdateNedozvoljenBroj() throws Exception {
        Fotografija Fotografija = new Fotografija(NEW_Fotografija_LOSE,1,100);
        Fotografija created = fotografijaService.update(Fotografija,VEC_KREIRANA_Fotografija );



        assertEquals(null, created);
    }*/

    @Test
    public void testDelete() throws Exception {
        fotografijaService.delete(VEC_KREIRANA_Fotografija);

        Fotografija savedFotografija = new Fotografija(0,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100);
        savedFotografija.setId(VEC_KREIRANA_Fotografija);

        verify(fotografijaRepository, times(1)).findById(VEC_KREIRANA_Fotografija);
    }
}
