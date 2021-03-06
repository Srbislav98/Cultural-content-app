package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.NovostDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.mapper.RestPageImpl;
import com.kts.cultural_content.model.Novost;
import com.kts.cultural_content.service.NovostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.kts.cultural_content.constants.NovostConstants.*;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class NovostControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NovostService novostService;

    private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    public void testGetAllNovosti() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<NovostDTO[]> responseEntity= restTemplate.exchange("/api/novosti", HttpMethod.GET,httpEntity,NovostDTO[].class);

        NovostDTO[] novosti = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testGetAllNovostiPageable() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<NovostDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<NovostDTO>>() { };

        ResponseEntity<RestPageImpl<NovostDTO>> responseEntity = restTemplate.exchange("/api/novosti/by-page?page=0&size=2", HttpMethod.GET, httpEntity, responseType);

        List<NovostDTO> novosti = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, novosti.size());
    }

    @Test
    public void testGetNovost() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<NovostDTO> responseEntity =
                restTemplate.exchange("/api/novosti/get/100", HttpMethod.GET,httpEntity,  NovostDTO.class);

        NovostDTO novost = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(novost);
        assertEquals(DB_NOVOST_NAZIV, novost.getNaziv());
    }

    @Test
    public void testGetNovostFail() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<NovostDTO> responseEntity =
                restTemplate.exchange("/api/novosti/get/9999", HttpMethod.GET,httpEntity,  NovostDTO.class);

        NovostDTO novost = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(novost);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateNovost() throws Exception {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");

        NovostDTO n = new NovostDTO(1, "nov", "haha", objSDF.parse("2020-12-28"));

        HttpEntity<NovostDTO> httpEntity = new HttpEntity<NovostDTO>(n, headers);
        int size = novostService.findAll().size(); // broj slogova pre ubacivanja novog

        ResponseEntity<NovostDTO> responseEntity =
                restTemplate.exchange("/api/novosti/create/kulturna-ponuda/100", HttpMethod.POST, httpEntity, NovostDTO.class);

        // provera odgovora servera
        NovostDTO novost = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(novost);
        assertEquals("nov", novost.getNaziv());

        List<Novost> novosti = novostService.findAll();
        assertEquals(size + 1, novosti.size()); // mora biti jedan vise slog sada nego pre
        // poslednja kategorija u listi treba da bude nova kategorija koja je ubacena u testu
        assertEquals("nov", novosti.get(novosti.size()-1).getNaziv());

        // uklanjamo dodatu kategoriju
        novostService.delete(novost.getId());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateNovostFail() throws Exception {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");

        NovostDTO n = new NovostDTO(100, "novost", "opis", objSDF.parse("2020-12-12"));

        HttpEntity<NovostDTO> httpEntity = new HttpEntity<NovostDTO>(n, headers);
        int size = novostService.findAll().size(); // broj slogova pre ubacivanja novog

        ResponseEntity<NovostDTO> responseEntity =
                restTemplate.exchange("/api/novosti/create/kulturna-ponuda/100", HttpMethod.POST, httpEntity, NovostDTO.class);

        // provera odgovora servera
        NovostDTO novost = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(novost);

    }
    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateNovost() throws Exception {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");

        NovostDTO n = new NovostDTO(105, "novitotalno", "opis5", objSDF.parse("2020-12-21"));

        HttpEntity<NovostDTO> httpEntity = new HttpEntity<NovostDTO>(n, headers);

        ResponseEntity<NovostDTO> responseEntity =
                restTemplate.exchange("/api/novosti/update/105", HttpMethod.PUT, httpEntity, NovostDTO.class);

        // provera odgovora servera
        NovostDTO novost = responseEntity.getBody();
        assertNotNull(novost);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(VEC_KREIRANA_NOVOST2, novost.getId());
        assertEquals("novitotalno", novost.getNaziv());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateNovostFail() throws Exception {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");

        NovostDTO n = new NovostDTO(9999, "nov", "haha", objSDF.parse("2020-12-28"));

        HttpEntity<NovostDTO> httpEntity = new HttpEntity<NovostDTO>(n, headers);

        ResponseEntity<NovostDTO> responseEntity =
                restTemplate.exchange("/api/novosti/update/9999", HttpMethod.PUT, httpEntity, NovostDTO.class);

        // provera odgovora servera
        NovostDTO novost = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(novost);
    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDeleteNovost() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<NovostDTO> httpEntity = new HttpEntity<NovostDTO>(headers);
        List<Novost> novosti = novostService.findAll();

        int size = novostService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/novosti/delete/105",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(size-1, novostService.findAll().size());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testDeleteNovostFail() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<NovostDTO> httpEntity = new HttpEntity<NovostDTO>(headers);
        List<Novost> novosti = novostService.findAll();

        int size = novostService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/novosti/delete/9999",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(size, novostService.findAll().size());

    }

}
