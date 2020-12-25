package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.mapper.RestPageImpl;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.service.OcenaService;
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

import java.util.List;

import static com.kts.cultural_content.constants.OcenaConstants.*;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class OcenaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OcenaService ocenaService;

    private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    public void testGetAllOcene(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<OcenaDTO[]> responseEntity= restTemplate.exchange("/api/ocene", HttpMethod.GET,httpEntity,OcenaDTO[].class);

        OcenaDTO[] ocene = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //assertEquals(FIND_ALL_NUMBER_OF_ITEMS, ocene.length);
    }

    @Test
    public void testGetOcenePageable(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<OcenaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<OcenaDTO>>() { };

        ResponseEntity<RestPageImpl<OcenaDTO>> responseEntity = restTemplate.exchange("/api/ocene/by-page?page=0&size=2", HttpMethod.GET,httpEntity,
                responseType);

        List<OcenaDTO> ocene = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, ocene.size());

    }

    @Test
    public void testGetOcena(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<OcenaDTO> responseEntity =
                restTemplate.exchange("/api/ocene/get/100", HttpMethod.GET,httpEntity,OcenaDTO.class);

        OcenaDTO ocene = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(ocene);

    }

    @Test
    public void testGetOcenaNePostoji(){
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<OcenaDTO> responseEntity =
                restTemplate.exchange("/api/ocene/get/99999", HttpMethod.GET,httpEntity,OcenaDTO.class);

        OcenaDTO ocene = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(ocene);

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testCreateOcena() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        OcenaDTO o = new OcenaDTO(null, NEW_OCENA_DOBRO, 1,100);

        HttpEntity<OcenaDTO> httpEntity = new HttpEntity<OcenaDTO>(o,headers);

        int size = ocenaService.findAll().size();

        ResponseEntity<OcenaDTO> responseEntity=
                restTemplate.exchange("/api/ocene/create", HttpMethod.POST,httpEntity, OcenaDTO.class);

        OcenaDTO ocena = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(ocena);
        assertEquals(NEW_OCENA_DOBRO, ocena.getVrednost());

        List<Ocena> ocene = ocenaService.findAll();
        assertEquals(size+1, ocene.size());
        assertEquals(NEW_OCENA_DOBRO, ocene.get(ocene.size()-1).getVrednost());

        ocenaService.delete(ocena.getId());
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testCreatePogresnaOcena() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        OcenaDTO o = new OcenaDTO(null, NEW_OCENA_LOSE, 1,100);

        HttpEntity<OcenaDTO> httpEntity = new HttpEntity<OcenaDTO>(o,headers);

        int size = ocenaService.findAll().size();

        ResponseEntity<OcenaDTO> responseEntity=
                restTemplate.exchange("/api/ocene/create", HttpMethod.POST,httpEntity, OcenaDTO.class);

        OcenaDTO ocena = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(ocena);
        //assertEquals(NEW_OCENA_LOSE, ocena.getVrednost());


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdateOcena() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        OcenaDTO o = new OcenaDTO(null,NEW_OCENA_DOBRO,1,100);

        HttpEntity<OcenaDTO> httpEntity = new HttpEntity<OcenaDTO>(o,headers);

        ResponseEntity<OcenaDTO> responseEntity =
                restTemplate.exchange("/api/ocene/update/100", HttpMethod.PUT,
                        httpEntity, OcenaDTO.class);

        OcenaDTO ocena = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(ocena);
        assertEquals(VEC_KREIRANA_OCENA, ocena.getId());
        assertEquals(NEW_OCENA_DOBRO,ocena.getVrednost());

        Ocena dbOcena = ocenaService.findOne(VEC_KREIRANA_OCENA);
        assertEquals(VEC_KREIRANA_OCENA, ocena.getId());
        assertEquals(NEW_OCENA_DOBRO, ocena.getVrednost());

        dbOcena.setVrednost(2);
        ocenaService.update(dbOcena,dbOcena.getId());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdateOcenaNePostoji() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        OcenaDTO o = new OcenaDTO(null,NEW_OCENA_DOBRO,1,100);

        HttpEntity<OcenaDTO> httpEntity = new HttpEntity<OcenaDTO>(o,headers);

        ResponseEntity<OcenaDTO> responseEntity =
                restTemplate.exchange("/api/ocene/update/9999999", HttpMethod.PUT,
                        httpEntity, OcenaDTO.class);

        OcenaDTO ocena = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(ocena);


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testUpdatePogresnaOcena() throws Exception{
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        OcenaDTO o = new OcenaDTO(null,NEW_OCENA_LOSE,1,100);

        HttpEntity<OcenaDTO> httpEntity = new HttpEntity<OcenaDTO>(o,headers);

        ResponseEntity<OcenaDTO> responseEntity =
                restTemplate.exchange("/api/ocene/update/1", HttpMethod.PUT,
                        httpEntity, OcenaDTO.class);

        OcenaDTO ocena = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(ocena);


    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testDeleteOcena() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        //OcenaDTO o = new OcenaDTO(DB_OCENA_ID,NEW_OCENA_DOBRO,1,100);

        HttpEntity<OcenaDTO> httpEntity = new HttpEntity<OcenaDTO>(headers);
        List<Ocena> ocenas = ocenaService.findAll();

        int size = ocenaService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/ocene/delete/101",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(size -1, ocenaService.findAll().size());

    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void testDeleteOcenaNePostoji() throws Exception{

        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        //OcenaDTO o = new OcenaDTO(DB_OCENA_ID,NEW_OCENA_DOBRO,1,100);

        HttpEntity<OcenaDTO> httpEntity = new HttpEntity<OcenaDTO>(headers);
        List<Ocena> ocenas = ocenaService.findAll();

        int size = ocenaService.findAll().size();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/ocene/delete/99999999999999",
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(size, ocenaService.findAll().size());

    }


}
