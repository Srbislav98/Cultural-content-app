package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.FotografijaDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.mapper.RestPageImpl;
import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.service.FotografijaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

import static com.kts.cultural_content.constants.FotografijaConstants.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class FotografijaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FotografijaService fotografijaService;

    private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    public void testGetAllfotografije(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<FotografijaDTO[]> responseEntity= restTemplate.exchange("/api/fotografije", HttpMethod.GET,httpEntity,FotografijaDTO[].class);

        FotografijaDTO[] fotografije = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //assertEquals(FIND_ALL_NUMBER_OF_ITEMS, fotografije.length);
    }

    /*@Test
    public void testGetfotografijePageable(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<FotografijaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<FotografijaDTO>>() { };

        ResponseEntity<RestPageImpl<FotografijaDTO>> responseEntity = restTemplate.exchange("/api/fotografije/by-page?page=0&size=2", HttpMethod.GET,httpEntity,
                responseType);

        List<FotografijaDTO> fotografije = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, fotografije.size());

    }*/

    @Test
    public void testGetFotografija(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<FotografijaDTO> responseEntity =
                restTemplate.exchange("/api/fotografije/get/100", HttpMethod.GET,httpEntity,FotografijaDTO.class);

        FotografijaDTO fotografije = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(fotografije);

    }

    @Test
    public void testGetFotografijaNePostoji(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<FotografijaDTO> responseEntity =
                restTemplate.exchange("/api/fotografije/get/99999", HttpMethod.GET,httpEntity,FotografijaDTO.class);

        FotografijaDTO fotografije = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(fotografije);

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testCreateFotografija() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        FotografijaDTO o = new FotografijaDTO(null, NEW_Fotografija_DOBRO, new File(NEW_Fotografija_DOBRO), 1,100);

        HttpEntity<FotografijaDTO> httpEntity = new HttpEntity<FotografijaDTO>(o,headers);

        int size = fotografijaService.findAll().size();

        ResponseEntity<FotografijaDTO> responseEntity=
                restTemplate.exchange("/api/fotografije/create", HttpMethod.POST,httpEntity, FotografijaDTO.class);

        FotografijaDTO Fotografija = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(Fotografija);
        assertEquals(NEW_Fotografija_DOBRO, Fotografija.getLokacijaFajl());

        List<Fotografija> fotografije = fotografijaService.findAll();
        assertEquals(size+1, fotografije.size());
        assertEquals(NEW_Fotografija_DOBRO, fotografije.get(fotografije.size()-1).getLokacijaFajl());

        fotografijaService.delete(Fotografija.getId());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testCreatePogresnaFotografija() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        FotografijaDTO o = new FotografijaDTO(null, NEW_Fotografija_LOSE,new File(NEW_Fotografija_DOBRO), 1,100);

        HttpEntity<FotografijaDTO> httpEntity = new HttpEntity<FotografijaDTO>(o,headers);

        int size = fotografijaService.findAll().size();

        ResponseEntity<FotografijaDTO> responseEntity=
                restTemplate.exchange("/api/fotografije/create", HttpMethod.POST,httpEntity, FotografijaDTO.class);

        FotografijaDTO Fotografija = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(Fotografija);
        //assertEquals(NEW_Fotografija_LOSE, Fotografija.getVrednost());


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdateFotografija() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        FotografijaDTO o = new FotografijaDTO(null,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100);

        HttpEntity<FotografijaDTO> httpEntity = new HttpEntity<FotografijaDTO>(o,headers);

        ResponseEntity<FotografijaDTO> responseEntity =
                restTemplate.exchange("/api/fotografije/update/100", HttpMethod.PUT,
                        httpEntity, FotografijaDTO.class);

        FotografijaDTO Fotografija = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(Fotografija);
        assertEquals(VEC_KREIRANA_Fotografija, Fotografija.getId());
        assertEquals(NEW_Fotografija_DOBRO,Fotografija.getLokacijaFajl());

        Fotografija dbFotografija = fotografijaService.findOne(VEC_KREIRANA_Fotografija);
        assertEquals(VEC_KREIRANA_Fotografija, Fotografija.getId());
        assertEquals(NEW_Fotografija_DOBRO, Fotografija.getLokacijaFajl());

        dbFotografija.setLokacijaFajl("C:/Users/Kssbc/Documents/GitHub/KTSNWT2020_T5/Cultural-content/Slike/OIP.jpg");
        fotografijaService.update(dbFotografija,dbFotografija.getId());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdateFotografijaNePostoji() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        FotografijaDTO o = new FotografijaDTO(null,NEW_Fotografija_DOBRO,new File(NEW_Fotografija_DOBRO),1,100);

        HttpEntity<FotografijaDTO> httpEntity = new HttpEntity<FotografijaDTO>(o,headers);

        ResponseEntity<FotografijaDTO> responseEntity =
                restTemplate.exchange("/api/fotografije/update/9999999", HttpMethod.PUT,
                        httpEntity, FotografijaDTO.class);

        FotografijaDTO Fotografija = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(Fotografija);


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdatePogresnaFotografija() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        FotografijaDTO o = new FotografijaDTO(null,NEW_Fotografija_LOSE,new File(NEW_Fotografija_DOBRO),1,100);

        HttpEntity<FotografijaDTO> httpEntity = new HttpEntity<FotografijaDTO>(o,headers);

        ResponseEntity<FotografijaDTO> responseEntity =
                restTemplate.exchange("/api/fotografije/update/1", HttpMethod.PUT,
                        httpEntity, FotografijaDTO.class);

        FotografijaDTO Fotografija = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(Fotografija);


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testDeleteFotografija() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        //FotografijaDTO o = new FotografijaDTO(DB_Fotografija_ID,NEW_Fotografija_DOBRO,1,100);

        HttpEntity<FotografijaDTO> httpEntity = new HttpEntity<FotografijaDTO>(headers);
        List<Fotografija> Fotografijas = fotografijaService.findAll();

        int size = fotografijaService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/fotografije/delete/101",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(size -1, fotografijaService.findAll().size());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testDeleteFotografijaNePostoji() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        //FotografijaDTO o = new FotografijaDTO(DB_Fotografija_ID,NEW_Fotografija_DOBRO,1,100);

        HttpEntity<FotografijaDTO> httpEntity = new HttpEntity<FotografijaDTO>(headers);
        List<Fotografija> Fotografijas = fotografijaService.findAll();

        int size = fotografijaService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/fotografije/delete/99999999999999",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(size, fotografijaService.findAll().size());

    }

}
