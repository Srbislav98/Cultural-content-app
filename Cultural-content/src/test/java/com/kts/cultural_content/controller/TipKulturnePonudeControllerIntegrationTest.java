package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.TipKulturnePonudeDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.model.TipKulturnePonude;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.TipKPRepository;
import com.kts.cultural_content.service.TipKPService;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TipKulturnePonudeControllerIntegrationTest {
    private String accessToken;

    @Autowired
    KulturnaPonudaRepository oKulturnaPonuda;

    @Autowired
    TipKPRepository oTip;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TipKPService KPService;

    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    @Order(1)
    public void testGetAllTipKulturnePonudes() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<TipKulturnePonudeDTO[]> responseEntity =
                restTemplate.exchange("/api/tipoviKP", HttpMethod.GET,httpEntity, TipKulturnePonudeDTO[].class);


        TipKulturnePonudeDTO[] tip = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println(tip[0].getId() + ") " + tip[0].getNaziv());
        System.out.println(tip[1].getId() + ") " + tip[1].getNaziv());
        if (tip.length == 1) {
            assertEquals(1, tip.length);
            assertEquals("obicna", tip[0].getNaziv());
        }else {
            assertEquals(2, tip.length);
            assertEquals("obicna", tip[0].getNaziv());
            assertEquals("TestNaziv", tip[1].getNaziv());
        }
    }

    @Test
    @Order(4)
    public void testGetTipKulturnePonude() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<TipKulturnePonudeDTO> responseEntity =
                restTemplate.exchange("/api/tipoviKP/get/100", HttpMethod.GET,httpEntity, TipKulturnePonudeDTO.class);


        TipKulturnePonudeDTO tip = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("obicna", tip.getNaziv());
    }

    @Test
    public void testGetTipKulturnePonudeNotExisting() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<TipKulturnePonudeDTO> responseEntity =
                restTemplate.exchange("/api/tipoviKP/get/666", HttpMethod.GET,httpEntity, TipKulturnePonudeDTO.class);


        TipKulturnePonudeDTO tip = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateTipKulturnePonude() throws Exception {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        TipKulturnePonudeDTO tip=new TipKulturnePonudeDTO(666,"TestNaziv");

        HttpEntity<TipKulturnePonudeDTO> httpEntity = new HttpEntity<TipKulturnePonudeDTO>(tip,headers);
        ResponseEntity<TipKulturnePonudeDTO> responseEntity =
                restTemplate.exchange("/api/tipoviKP/create", HttpMethod.POST,httpEntity, TipKulturnePonudeDTO.class);


        TipKulturnePonudeDTO tip2 = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("TestNaziv", tip2.getNaziv());
        TipKulturnePonude backup= oTip.findByNaziv("TestNaziv").get(0);

        KPService.delete(backup.getId());
        assertNull(null);
    }

    @Test
    @Order(2)
    @Transactional
    @Rollback(true)
    public void testCreateTipKulturnePonudeNazivExists() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        System.out.println("obicna");
        System.out.println("-------------");
        for (TipKulturnePonude tt: KPService.findAll()) {
            System.out.println("nasao : " + tt.getNaziv());
        }

        TipKulturnePonudeDTO tip=new TipKulturnePonudeDTO(666,"obicna");

        HttpEntity<TipKulturnePonudeDTO> httpEntity = new HttpEntity<TipKulturnePonudeDTO>(tip,headers);
        ResponseEntity<TipKulturnePonudeDTO> responseEntity =
                restTemplate.exchange("/api/tipoviKP/create", HttpMethod.POST,httpEntity, TipKulturnePonudeDTO.class);


        TipKulturnePonudeDTO tip2 = responseEntity.getBody();

        Boolean prvi = responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST;

        tip=new TipKulturnePonudeDTO(666,"NoviTestNaziv");
        HttpEntity<TipKulturnePonudeDTO> httpEntity2 = new HttpEntity<TipKulturnePonudeDTO>(tip,headers);
        responseEntity =
                restTemplate.exchange("/api/tipoviKP/create", HttpMethod.POST,httpEntity2, TipKulturnePonudeDTO.class);


        tip2 = responseEntity.getBody();
        Boolean drugi = HttpStatus.BAD_REQUEST == responseEntity.getStatusCode();

        assertTrue(prvi || drugi);
    }

    @Test
    @Transactional
    @Rollback(true)
    @Order(5)
    public void testUpdateTipKulturnePonude() throws Exception {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        TipKulturnePonude backup= oTip.findByNaziv("obicna").get(0);

        TipKulturnePonudeDTO tip=new TipKulturnePonudeDTO(666,"NoviTestNaziv");

        HttpEntity<TipKulturnePonudeDTO> httpEntity = new HttpEntity<TipKulturnePonudeDTO>(tip,headers);
        ResponseEntity<TipKulturnePonudeDTO> responseEntity =
                restTemplate.exchange("/api/tipoviKP/update/100", HttpMethod.PUT,httpEntity, TipKulturnePonudeDTO.class);


        TipKulturnePonudeDTO tip2 = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("NoviTestNaziv", tip2.getNaziv());

        KPService.update(backup,100);
        assertNull(null);
    }

    @Test
    @Order(3)
    @Transactional
    @Rollback(true)
    public void testUpdateTipKulturnePonudeIdNotExist() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        TipKulturnePonudeDTO tip=new TipKulturnePonudeDTO(666,"NoviTestNaziv");

        HttpEntity<TipKulturnePonudeDTO> httpEntity = new HttpEntity<TipKulturnePonudeDTO>(tip,headers);
        ResponseEntity<TipKulturnePonudeDTO> responseEntity =
                restTemplate.exchange("/api/tipoviKP/update/666", HttpMethod.PUT,httpEntity, TipKulturnePonudeDTO.class);


        TipKulturnePonudeDTO tip2 = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    @Test
    @Order(6)
    @Transactional
    @Rollback(true)
    public void testDeleteTipKulturnePonude() throws Exception {
        TipKulturnePonude backup= oTip.findByNaziv("obicna").get(0);
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<TipKulturnePonudeDTO> httpEntity = new HttpEntity<TipKulturnePonudeDTO>(headers);
        ResponseEntity<TipKulturnePonudeDTO> responseEntity =
                restTemplate.exchange("/api/tipoviKP/delete/100", HttpMethod.DELETE,httpEntity, TipKulturnePonudeDTO.class);


        TipKulturnePonudeDTO tip = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        KPService.create(backup);
        assertNull(null);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteTipKulturnePonudeNotFound() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<TipKulturnePonudeDTO> httpEntity = new HttpEntity<TipKulturnePonudeDTO>(headers);
        ResponseEntity<TipKulturnePonudeDTO> responseEntity =
                restTemplate.exchange("/api/tipoviKP/delete/666", HttpMethod.DELETE,httpEntity, TipKulturnePonudeDTO.class);


        TipKulturnePonudeDTO tip = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
