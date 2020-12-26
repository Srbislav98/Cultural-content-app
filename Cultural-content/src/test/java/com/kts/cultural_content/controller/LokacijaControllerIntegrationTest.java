package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.LokacijaDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.service.LokacijaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.kts.cultural_content.constants.LokacijaConstants.DB_LOKACIJA_NAZIV;
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



}
