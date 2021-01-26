package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.*;
import com.kts.cultural_content.service.LokacijaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.kts.cultural_content.constants.LokacijaConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class LokacijaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String accessToken;

    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    public void testGetLokacijaPoNazivu() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<LokacijaDTO> responseEntity =
                restTemplate.exchange("/api/lokacije/getByNaziv/Novi Sad", HttpMethod.GET,httpEntity,  LokacijaDTO.class);

        LokacijaDTO lokacija = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(lokacija);
        assertEquals(DB_LOKACIJA_NAZIV, lokacija.getNazivLokacije());
    }

    @Test
    public void testGetLokacijaPoNazivuFail() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ResponseEntity<LokacijaDTO> responseEntity =
                restTemplate.exchange("/api/lokacije/getByNaziv/tralala", HttpMethod.GET,httpEntity,  LokacijaDTO.class);

        LokacijaDTO lokacija = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(lokacija);
    }

    @Test
    public void testGetAllLokacije() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<LokacijaDTO[]> responseEntity= restTemplate.exchange("/api/lokacije", HttpMethod.GET,httpEntity, LokacijaDTO[].class);

        LokacijaDTO[] lokacijeDTOs = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS, lokacijeDTOs.length);
    }

    @Test
    public void getLokacijaPoIdKulturnePonude() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<LokacijaDTO> responseEntity =
                restTemplate.exchange("/api/lokacije/getById/100", HttpMethod.GET,httpEntity,  LokacijaDTO.class);

        LokacijaDTO lokacijaDTO = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(lokacijaDTO);
        assertEquals(DB_LOKACIJA_NAZIV, lokacijaDTO.getNazivLokacije());
    }

    @Test
    public void getLokacijaPoIdKulturnePonudeFail() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<LokacijaDTO> responseEntity =
                restTemplate.exchange("/api/lokacije/getById/9999", HttpMethod.GET,httpEntity,  LokacijaDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getMapLocationsByIds() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        List<Integer> ids = new ArrayList<>();
        ids.add(100);

        HttpEntity<List<Integer>> httpEntity = new HttpEntity<>(ids, headers);

        ParameterizedTypeReference<List<LokacijaNaMapiDTO>> responseType = new ParameterizedTypeReference<List<LokacijaNaMapiDTO>>() { };

        ResponseEntity<List<LokacijaNaMapiDTO>> responseEntity =
                restTemplate.exchange("/api/lokacije/getLocationsIds", HttpMethod.POST, httpEntity,  responseType);

        List<LokacijaNaMapiDTO> lokacijaNaMapiDTOs = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(lokacijaNaMapiDTOs);
        assertEquals(FIND_ALL_NUMBER_OF_ITEMS_MAP, lokacijaNaMapiDTOs.size());
        assertEquals(DB_LOKACIJA_NA_MAPI_LONGITUDE, (Double) lokacijaNaMapiDTOs.get(0).getGeoDuzina());
        assertEquals(DB_LOKACIJA_NA_MAPI_LATITUDE, (Double) lokacijaNaMapiDTOs.get(0).getGeoSirina());
    }

}
