package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.RecenzijaDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.mapper.RestPageImpl;
import com.kts.cultural_content.model.Recenzija;
import com.kts.cultural_content.service.RecenzijaService;
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

import java.io.File;
import java.util.List;

import static com.kts.cultural_content.constants.RecenzijaConstants.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RecenzijaControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RecenzijaService RecenzijaService;

    private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    public void testGetAllRecenzija(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<RecenzijaDTO[]> responseEntity= restTemplate.exchange("/api/recenzije", HttpMethod.GET,httpEntity,RecenzijaDTO[].class);

        RecenzijaDTO[] Recenzijai = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //assertEquals(FIND_ALL_NUMBER_OF_ITEMS, Recenzijai.length);
    }

    @Test
    public void testGetRecenzijaiPageable(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<RecenzijaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<RecenzijaDTO>>() { };

        ResponseEntity<RestPageImpl<RecenzijaDTO>> responseEntity = restTemplate.exchange("/api/recenzije/by-page?page=0&size=2", HttpMethod.GET,httpEntity,
                responseType);

        List<RecenzijaDTO> Recenzijai = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, Recenzijai.size());

    }

    @Test
    public void testGetRecenzija(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RecenzijaDTO> responseEntity =
                restTemplate.exchange("/api/recenzije/get/100", HttpMethod.GET,httpEntity,RecenzijaDTO.class);

        RecenzijaDTO Recenzijai = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(Recenzijai);

    }

    @Test
    public void testGetRecenzijaNePostoji(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<RecenzijaDTO> responseEntity =
                restTemplate.exchange("/api/recenzije/get/99999", HttpMethod.GET,httpEntity,RecenzijaDTO.class);

        RecenzijaDTO Recenzijai = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(Recenzijai);

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testCreateRecenzija() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        RecenzijaDTO o = new RecenzijaDTO(null, NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, new File(NEW_Fotografija_DOBRO));

        HttpEntity<RecenzijaDTO> httpEntity = new HttpEntity<RecenzijaDTO>(o,headers);

        int size = RecenzijaService.findAll().size();

        ResponseEntity<RecenzijaDTO> responseEntity=
                restTemplate.exchange("/api/recenzije/create", HttpMethod.POST,httpEntity, RecenzijaDTO.class);

        RecenzijaDTO Recenzija = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(Recenzija);
        assertEquals(NEW_OCENA_DOBRO, Recenzija.getOcena());
        assertEquals(NEW_Komentar_DOBRO, Recenzija.getKomentar());
        assertEquals(NEW_Fotografija_DOBRO, Recenzija.getFoto());

        List<com.kts.cultural_content.model.Recenzija> Recenzijai = RecenzijaService.findAll();
        assertEquals(size+1, Recenzijai.size());
        assertEquals(NEW_OCENA_DOBRO, Recenzijai.get(Recenzijai.size()-1).getOcena());
        assertEquals(NEW_Fotografija_DOBRO, Recenzijai.get(Recenzijai.size()-1).getFoto());
        assertEquals(NEW_Komentar_DOBRO, Recenzijai.get(Recenzijai.size()-1).getKomentar());

        RecenzijaService.delete(Recenzija.getId());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testCreatePogresnaRecenzija() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        RecenzijaDTO o = new RecenzijaDTO(null,  NEW_OCENA_LOSE, NEW_Komentar_DOBRO, 1,100, new File(NEW_Fotografija_DOBRO));

        HttpEntity<RecenzijaDTO> httpEntity = new HttpEntity<RecenzijaDTO>(o,headers);

        int size = RecenzijaService.findAll().size();

        ResponseEntity<RecenzijaDTO> responseEntity=
                restTemplate.exchange("/api/recenzije/create", HttpMethod.POST,httpEntity, RecenzijaDTO.class);

        RecenzijaDTO Recenzija = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(Recenzija);
        //assertEquals(NEW_Recenzija_LOSE, Recenzija.getVrednost());


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdateRecenzija() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        RecenzijaDTO o = new RecenzijaDTO(null, NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, new File(NEW_Fotografija_DOBRO));

        HttpEntity<RecenzijaDTO> httpEntity = new HttpEntity<RecenzijaDTO>(o,headers);

        ResponseEntity<RecenzijaDTO> responseEntity =
                restTemplate.exchange("/api/recenzije/update/100", HttpMethod.PUT,
                        httpEntity, RecenzijaDTO.class);

        RecenzijaDTO Recenzija = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(Recenzija);
        assertEquals(VEC_KREIRANA_Recenzija, Recenzija.getId());
        assertEquals(NEW_OCENA_DOBRO, Recenzija.getOcena());
        assertEquals(NEW_Komentar_DOBRO,Recenzija.getKomentar());

        Recenzija dbRecenzija = RecenzijaService.findOne(VEC_KREIRANA_Recenzija);
        assertEquals(VEC_KREIRANA_Recenzija, Recenzija.getId());
        assertEquals(NEW_OCENA_DOBRO, Recenzija.getOcena());
        assertEquals(NEW_Komentar_DOBRO, Recenzija.getKomentar());

        dbRecenzija.setOcena(4);
        dbRecenzija.setKomentar("wdwdwdwd");
        RecenzijaService.update(dbRecenzija,dbRecenzija.getId());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdateRecenzijaNePostoji() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        RecenzijaDTO o = new RecenzijaDTO(null, NEW_OCENA_DOBRO, NEW_Komentar_DOBRO, 1,100, new File(NEW_Fotografija_DOBRO));

        HttpEntity<RecenzijaDTO> httpEntity = new HttpEntity<RecenzijaDTO>(o,headers);

        ResponseEntity<RecenzijaDTO> responseEntity =
                restTemplate.exchange("/api/recenzije/update/9999999", HttpMethod.PUT,
                        httpEntity, RecenzijaDTO.class);

        RecenzijaDTO Recenzija = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(Recenzija);


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdatePogresnaRecenzija() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        RecenzijaDTO o = new RecenzijaDTO(null, NEW_OCENA_LOSE, NEW_Komentar_DOBRO, 1,100, new File(NEW_Fotografija_DOBRO));

        HttpEntity<RecenzijaDTO> httpEntity = new HttpEntity<RecenzijaDTO>(o,headers);

        ResponseEntity<RecenzijaDTO> responseEntity =
                restTemplate.exchange("/api/recenzije/update/1", HttpMethod.PUT,
                        httpEntity, RecenzijaDTO.class);

        RecenzijaDTO Recenzija = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(Recenzija);


    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDeleteRecenzija() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        //RecenzijaDTO o = new RecenzijaDTO(DB_Recenzija_ID,NEW_Recenzija_DOBRO,1,100);

        HttpEntity<RecenzijaDTO> httpEntity = new HttpEntity<RecenzijaDTO>(headers);
        List<Recenzija> Recenzijas = RecenzijaService.findAll();

        int size = RecenzijaService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/recenzije/delete/101",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(size -1, RecenzijaService.findAll().size());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testDeleteRecenzijaNePostoji() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        //RecenzijaDTO o = new RecenzijaDTO(DB_Recenzija_ID,NEW_Recenzija_DOBRO,1,100);

        HttpEntity<RecenzijaDTO> httpEntity = new HttpEntity<RecenzijaDTO>(headers);
        List<Recenzija> Recenzijas = RecenzijaService.findAll();

        int size = RecenzijaService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/recenzije/delete/99999999999999",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(size, RecenzijaService.findAll().size());

    }
}
