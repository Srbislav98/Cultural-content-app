package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Lokacija;
import com.kts.cultural_content.model.TipKulturnePonude;
import com.kts.cultural_content.repository.AdminRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.LokacijaRepository;
import com.kts.cultural_content.repository.TipKPRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KulturnaPonudaServiceIntegrationTest {
    private String accessToken;

    @Autowired
    KulturnaPonudaService kulturnaPonudaService;

    @Autowired
    KulturnaPonudaRepository oKulturnaPonuda;

    @Autowired
    AdminRepository oAdmin;

    @Autowired
    LokacijaRepository oLokacija;

    @Autowired
    TipKPRepository oTip;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    AuthenticationManager authenticationManager;

    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    public void testFindAll() {
        List<KulturnaPonuda> found = kulturnaPonudaService.findAll();
        assertEquals(4, found.size());
        assertEquals("kulturnaponuda", found.get(0).getNaziv());
        assertEquals("nekulturnaponuda", found.get(1).getNaziv());
        assertEquals("ponuda", found.get(2).getNaziv());
        assertEquals("glhf", found.get(3).getNaziv());
    }

    @Test
    public void testFindAllPageable() {
        Pageable pageable = PageRequest.of(0,2);
        Page<KulturnaPonuda> found = kulturnaPonudaService.findAll(pageable);
        assertEquals(2, found.getNumberOfElements());
    }

    @Test
    public void testFindOne() {
        KulturnaPonuda found = kulturnaPonudaService.findOne(100);
        assertEquals("100", found.getId().toString());
    }

    @Test
    @Transactional
    public void testCreatewithKP() throws Exception {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        //UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken("aUserName", "aPassword");
        //Mockito.when(authentication.getPrincipal()).thenReturn(principal);
        //SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("124@gmail.com", "admin"));

        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);


        Admin admin = oAdmin.getOne(2);
        Lokacija lokacija = new Lokacija(666, "100", 666, 666);
        TipKulturnePonude tip = new TipKulturnePonude(666, "100");

        KulturnaPonuda k = new KulturnaPonuda(666,"KreiranaKulturnaPonuda","Topolska 18","...",tip ,admin,lokacija);
        KulturnaPonuda created = kulturnaPonudaService.create(k);
        assertEquals(k.getNaziv(), created.getNaziv());
        k = oKulturnaPonuda.findByNaziv(k.getNaziv()).get(0);// override-ovace id
        kulturnaPonudaService.delete(k.getId());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("124@gmail.com", "admin"));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        Admin admin = oAdmin.getOne(2);
        Lokacija lokacija = new Lokacija(666, "100", 666, 666);
        TipKulturnePonude tip = new TipKulturnePonude(666, "100");

        KulturnaPonuda k = new KulturnaPonuda(666,"KreiranaKulturnaPonuda","Topolska 18","...",tip ,admin,lokacija);
        KulturnaPonuda updated = kulturnaPonudaService.update(k, 101);
        assertEquals(k.getNaziv(),updated.getNaziv());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        /*login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("124@gmail.com", "admin"));
        SecurityContextHolder.getContext().setAuthentication(authentication);*/


        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken("124@gmail.com", "admin"));
        SecurityContextHolder.getContext().setAuthentication(authentication);



        KulturnaPonuda k = oKulturnaPonuda.getOne(102);

        Lokacija lokacija = oLokacija.findById(k.getLokacija().getId()).get();
        TipKulturnePonude tip = oTip.findById(k.getTipKulturnePonude().getId()).get();

        kulturnaPonudaService.delete(102);



        Optional<KulturnaPonuda> b = oKulturnaPonuda.findById(102);
        assertFalse(b.isPresent());

        System.out.println("br admina " + oAdmin.findAll().size());

        Admin admin = oAdmin.getOne(2);
        System.out.println("/////////////////////////////////////////admin////");
        System.out.println(admin.getId());
        System.out.println(admin.getEmail());
        System.out.println(admin.getIme());

        tip.setNaziv(tip.getId().toString());
        lokacija.setNazivLokacije(lokacija.getId().toString());

        k.setTipKulturnePonude(tip);
        k.setLokacija(lokacija);
        k.setId(666);
        //kulturnaPonudaService.create(k);
    }
}
