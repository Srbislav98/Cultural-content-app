package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.KomentarDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.mapper.RestPageImpl;
import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.service.KomentarService;
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

import java.util.List;

import static com.kts.cultural_content.constants.KomentarConstants.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KomentarControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private KomentarService komentarService;

    private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    public void testGetAllKomentar(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<KomentarDTO[]> responseEntity= restTemplate.exchange("/api/komentari", HttpMethod.GET,httpEntity,KomentarDTO[].class);

        KomentarDTO[] komentari = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //assertEquals(FIND_ALL_NUMBER_OF_ITEMS, komentari.length);
    }

    @Test
    public void testGetKomentariPageable(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<KomentarDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<KomentarDTO>>() { };

        ResponseEntity<RestPageImpl<KomentarDTO>> responseEntity = restTemplate.exchange("/api/komentari/by-page?page=0&size=2", HttpMethod.GET,httpEntity,
                responseType);

        List<KomentarDTO> komentari = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, komentari.size());

    }

    @Test
    public void testGetKomentar(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<KomentarDTO> responseEntity =
                restTemplate.exchange("/api/komentari/get/100", HttpMethod.GET,httpEntity,KomentarDTO.class);

        KomentarDTO komentari = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(komentari);

    }

    @Test
    public void testGetKomentarNePostoji(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<KomentarDTO> responseEntity =
                restTemplate.exchange("/api/komentari/get/99999", HttpMethod.GET,httpEntity,KomentarDTO.class);

        KomentarDTO komentari = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(komentari);

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testCreateKomentar() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KomentarDTO o = new KomentarDTO(null, NEW_Komentar_DOBRO, 1,100);

        HttpEntity<KomentarDTO> httpEntity = new HttpEntity<KomentarDTO>(o,headers);

        int size = komentarService.findAll().size();

        ResponseEntity<KomentarDTO> responseEntity=
                restTemplate.exchange("/api/komentari/create", HttpMethod.POST,httpEntity, KomentarDTO.class);

        KomentarDTO Komentar = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(Komentar);
        assertEquals(NEW_Komentar_DOBRO, Komentar.getVrednost());

        List<Komentar> komentari = komentarService.findAll();
        assertEquals(size+1, komentari.size());
        assertEquals(NEW_Komentar_DOBRO, komentari.get(komentari.size()-1).getVrednost());

        komentarService.delete(Komentar.getId());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testCreatePogresnaKomentar() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KomentarDTO o = new KomentarDTO(null, NEW_Komentar_LOSE, 1,100);

        HttpEntity<KomentarDTO> httpEntity = new HttpEntity<KomentarDTO>(o,headers);

        int size = komentarService.findAll().size();

        ResponseEntity<KomentarDTO> responseEntity=
                restTemplate.exchange("/api/komentari/create", HttpMethod.POST,httpEntity, KomentarDTO.class);

        KomentarDTO Komentar = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(Komentar);
        //assertEquals(NEW_Komentar_LOSE, Komentar.getVrednost());


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdateKomentar() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KomentarDTO o = new KomentarDTO(null,NEW_Komentar_DOBRO,1,100);

        HttpEntity<KomentarDTO> httpEntity = new HttpEntity<KomentarDTO>(o,headers);

        ResponseEntity<KomentarDTO> responseEntity =
                restTemplate.exchange("/api/komentari/update/100", HttpMethod.PUT,
                        httpEntity, KomentarDTO.class);

        KomentarDTO Komentar = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(Komentar);
        assertEquals(VEC_KREIRANA_Komentar, Komentar.getId());
        assertEquals(NEW_Komentar_DOBRO,Komentar.getVrednost());

        Komentar dbKomentar = komentarService.findOne(VEC_KREIRANA_Komentar);
        assertEquals(VEC_KREIRANA_Komentar, Komentar.getId());
        assertEquals(NEW_Komentar_DOBRO, Komentar.getVrednost());

        dbKomentar.setVrednost("wdwdwdwd");
        komentarService.update(dbKomentar,dbKomentar.getId());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdateKomentarNePostoji() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KomentarDTO o = new KomentarDTO(null,NEW_Komentar_DOBRO,1,100);

        HttpEntity<KomentarDTO> httpEntity = new HttpEntity<KomentarDTO>(o,headers);

        ResponseEntity<KomentarDTO> responseEntity =
                restTemplate.exchange("/api/komentari/update/9999999", HttpMethod.PUT,
                        httpEntity, KomentarDTO.class);

        KomentarDTO Komentar = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(Komentar);


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdatePogresnaKomentar() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KomentarDTO o = new KomentarDTO(null,NEW_Komentar_LOSE,1,100);

        HttpEntity<KomentarDTO> httpEntity = new HttpEntity<KomentarDTO>(o,headers);

        ResponseEntity<KomentarDTO> responseEntity =
                restTemplate.exchange("/api/komentari/update/1", HttpMethod.PUT,
                        httpEntity, KomentarDTO.class);

        KomentarDTO Komentar = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(Komentar);


    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDeleteKomentar() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        //KomentarDTO o = new KomentarDTO(DB_Komentar_ID,NEW_Komentar_DOBRO,1,100);

        HttpEntity<KomentarDTO> httpEntity = new HttpEntity<KomentarDTO>(headers);
        List<Komentar> Komentars = komentarService.findAll();

        int size = komentarService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/komentari/delete/101",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(size -1, komentarService.findAll().size());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testDeleteKomentarNePostoji() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        //KomentarDTO o = new KomentarDTO(DB_Komentar_ID,NEW_Komentar_DOBRO,1,100);

        HttpEntity<KomentarDTO> httpEntity = new HttpEntity<KomentarDTO>(headers);
        List<Komentar> Komentars = komentarService.findAll();

        int size = komentarService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/komentari/delete/99999999999999",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(size, komentarService.findAll().size());

    }

}
