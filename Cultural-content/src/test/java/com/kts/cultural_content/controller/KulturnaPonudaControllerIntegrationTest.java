package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.*;
import com.kts.cultural_content.mapper.RestPageImpl;
import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.model.Novost;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.TipKPRepository;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KulturnaPonudaControllerIntegrationTest {
    private String accessToken;

    @Autowired
    KulturnaPonudaRepository oKulturnaPonuda;

    @Autowired
    TipKPRepository oTip;

    @Autowired
    TestRestTemplate restTemplate;

    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllKulturnaPonudas() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<KulturnaPonudaDTO[]> responseEntity =
                restTemplate.exchange("/api/kulturnePonude", HttpMethod.GET,httpEntity, KulturnaPonudaDTO[].class);


        KulturnaPonudaDTO[] kp = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(4, kp.length);
        assertEquals("kulturnaponuda", kp[0].getNaziv());
        assertEquals("nekulturnaponuda", kp[1].getNaziv());
        assertEquals("ponuda", kp[2].getNaziv());
        assertEquals("glhf", kp[3].getNaziv());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllKulturnaPonidasPageable() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>> responseType =
                new ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>>() { };
        ResponseEntity<RestPageImpl<KulturnaPonudaDTO>> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/by-page?page=0&size=2",
                        HttpMethod.GET, httpEntity, responseType);
        List<KulturnaPonudaDTO> kp = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, kp.size());
        assertEquals("kulturnaponuda", kp.get(0).getNaziv());
        assertEquals("nekulturnaponuda", kp.get(1).getNaziv());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetKulturnaPonuda() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/get/100", HttpMethod.GET,httpEntity, KulturnaPonudaDTO.class);


        KulturnaPonudaDTO kp = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("kulturnaponuda", kp.getNaziv());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetKulturnaPonudaNotExisting() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/get/666", HttpMethod.GET,httpEntity, KulturnaPonudaDTO.class);


        KulturnaPonudaDTO kp = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testCreateKulturnaPonuda() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KulturnaPonudaDTO kp=new KulturnaPonudaDTO(666,"TestNaziv","Topolska 18","...",100,100);

        HttpEntity<KulturnaPonudaDTO> httpEntity = new HttpEntity<KulturnaPonudaDTO>(kp,headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/create", HttpMethod.POST,httpEntity, KulturnaPonudaDTO.class);


        KulturnaPonudaDTO kps = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("TestNaziv", kps.getNaziv());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateKulturnaPonudaNazivExists() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KulturnaPonudaDTO kp=new KulturnaPonudaDTO(666,"kulturnaponuda","Topolska 18","...",100,100);

        HttpEntity<KulturnaPonudaDTO> httpEntity = new HttpEntity<KulturnaPonudaDTO>(kp,headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/create", HttpMethod.POST,httpEntity, KulturnaPonudaDTO.class);


        KulturnaPonudaDTO kps = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateKulturnaPonudaTipDoesntExist() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KulturnaPonudaDTO kp=new KulturnaPonudaDTO(666,"TestNaziv","Topolska 18","...",666,100);

        HttpEntity<KulturnaPonudaDTO> httpEntity = new HttpEntity<KulturnaPonudaDTO>(kp,headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/create", HttpMethod.POST,httpEntity, KulturnaPonudaDTO.class);


        KulturnaPonudaDTO kps = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateKulturnaPonudaLokacijaDoesntExist() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KulturnaPonudaDTO kp=new KulturnaPonudaDTO(666,"TestNaziv","Topolska 18","...",100,666);

        HttpEntity<KulturnaPonudaDTO> httpEntity = new HttpEntity<KulturnaPonudaDTO>(kp,headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/create", HttpMethod.POST,httpEntity, KulturnaPonudaDTO.class);


        KulturnaPonudaDTO kps = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateKulturnaPonuda() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KulturnaPonudaDTO kp=new KulturnaPonudaDTO(666,"kulturnaponuda","Topolska 19","!?",100,100);

        HttpEntity<KulturnaPonudaDTO> httpEntity = new HttpEntity<KulturnaPonudaDTO>(kp,headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/update/100", HttpMethod.PUT,httpEntity, KulturnaPonudaDTO.class);


        KulturnaPonudaDTO kp2 = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Topolska 19", kp2.getAdresa());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateKulturnaPonudaIdNotExist() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        KulturnaPonudaDTO kp=new KulturnaPonudaDTO(666,"NoviTestNaziv","Topolska 19","!?",100,100);

        HttpEntity<KulturnaPonudaDTO> httpEntity = new HttpEntity<KulturnaPonudaDTO>(kp,headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/update/666", HttpMethod.PUT,httpEntity, KulturnaPonudaDTO.class);
        //                                                            /\ id se sanje preko url-a

        KulturnaPonudaDTO kp2 = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteKulturnaPonuda() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<KulturnaPonudaDTO> httpEntity = new HttpEntity<KulturnaPonudaDTO>(headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/delete/103", HttpMethod.DELETE,httpEntity, KulturnaPonudaDTO.class);


        KulturnaPonudaDTO kp = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteKulturnaPonudaNotFound() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<KulturnaPonudaDTO> httpEntity = new HttpEntity<KulturnaPonudaDTO>(headers);
        ResponseEntity<KulturnaPonudaDTO> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/delete/666", HttpMethod.DELETE,httpEntity, KulturnaPonudaDTO.class);


        KulturnaPonudaDTO kp = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetNovosti() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        ParameterizedTypeReference<RestPageImpl<NovostDTO>> responseType =
                new ParameterizedTypeReference<RestPageImpl<NovostDTO>>() { };

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<RestPageImpl<NovostDTO>> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/getNovosti/100", HttpMethod.GET, httpEntity, responseType);

        List<NovostDTO> kp = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetRecenzije() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        ParameterizedTypeReference<RestPageImpl<RecenzijaDTO>> responseType =
                new ParameterizedTypeReference<RestPageImpl<RecenzijaDTO>>() { };

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<RestPageImpl<RecenzijaDTO>> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/getRecenzije/100", HttpMethod.GET, httpEntity, responseType);

        List<RecenzijaDTO> kp = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetRecenzijeFilter() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        ParameterizedTypeReference<RestPageImpl<RecenzijaDTO>> responseType =
                new ParameterizedTypeReference<RestPageImpl<RecenzijaDTO>>() { };

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<RestPageImpl<RecenzijaDTO>> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/filter-by-grade/4/kulturna/100", HttpMethod.GET, httpEntity, responseType);

        List<RecenzijaDTO> kp = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetRecenzijeNePostoji() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        ParameterizedTypeReference<RestPageImpl<RecenzijaDTO>> responseType =
                new ParameterizedTypeReference<RestPageImpl<RecenzijaDTO>>() { };

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<RestPageImpl<RecenzijaDTO>> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/getRecenzije/9999", HttpMethod.GET, httpEntity, responseType);

        //List<RecenzijaDTO> kp = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetNovostiNePostoji() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        ParameterizedTypeReference<RestPageImpl<NovostDTO>> responseType =
                new ParameterizedTypeReference<RestPageImpl<NovostDTO>>() { };

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<RestPageImpl<NovostDTO>> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/getNovosti/99999999", HttpMethod.GET, httpEntity, responseType);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetProsecnaOcena() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Float> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/getProsecnaOcena/100", HttpMethod.GET, httpEntity, Float.class);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetProsecnaOcenaNePostoji() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Float> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/getProsecnaOcena/99999999", HttpMethod.GET, httpEntity, Float.class);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDaLiPostoji() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Boolean> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/daLiSadrzi/100/registrovani/1", HttpMethod.GET, httpEntity, Boolean.class);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testVecDaoReview() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Boolean> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/vecDaoReview/100/registrovani/1", HttpMethod.GET, httpEntity, Boolean.class);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testVecDaoReviewPogresanId() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Boolean> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/vecDaoReview/9999/registrovani/1", HttpMethod.GET, httpEntity, Boolean.class);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDaLiPostojiPogresanId() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Boolean> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/daLiSadrzi/9999/registrovani/1", HttpMethod.GET, httpEntity, Boolean.class);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void DajSlikuKul() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Fotografija> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/getSlikaKul/100", HttpMethod.GET, httpEntity, Fotografija.class);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void DajSlikuKulPogresanId() {
        login("124@gmail.com", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);


        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<Fotografija> responseEntity =
                restTemplate.exchange("/api/kulturnePonude/getSlikaKul/9999", HttpMethod.GET, httpEntity, Fotografija.class);


        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
