package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.mapper.RestPageImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static com.kts.cultural_content.constants.KPFiltersConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class KPFiltersControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }

    @Test
    public void testFilterByContent() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<KulturnaPonudaDTO[]> responseEntity= restTemplate.exchange("/api/kulturnePonude/filter-by-content/g", HttpMethod.GET,httpEntity,KulturnaPonudaDTO[].class);

        KulturnaPonudaDTO[] kulturnePonude = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(kulturnePonude);
        assertEquals(FIND_ALL_FILTERED_BY_CONTENT_NUMBER_OF_ITEMS, kulturnePonude.length);
    }

    @Test
    public void testFilterByContentFail() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<KulturnaPonudaDTO[]> responseEntity= restTemplate.exchange("/api/kulturnePonude/filter-by-content/jakepaul", HttpMethod.GET,httpEntity,KulturnaPonudaDTO[].class);

        KulturnaPonudaDTO[] kulturnePonude = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, kulturnePonude.length);
    }

    @Test
    public void testFilterByLocation() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<KulturnaPonudaDTO[]> responseEntity= restTemplate.exchange("/api/kulturnePonude/filter-by-location/Novi Sad", HttpMethod.GET,httpEntity,KulturnaPonudaDTO[].class);

        KulturnaPonudaDTO[] kulturnePonude = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(kulturnePonude);
        assertEquals(FIND_ALL_FILTERED_BY_LOCATION_NUMBER_OF_ITEMS, kulturnePonude.length);
    }

    @Test
    public void testFilterByLocationFail() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<KulturnaPonudaDTO[]> responseEntity= restTemplate.exchange("/api/kulturnePonude/filter-by-location/Nedodjija", HttpMethod.GET,httpEntity,KulturnaPonudaDTO[].class);

        KulturnaPonudaDTO[] kulturnePonude = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, kulturnePonude.length);
    }

    @Test
    public void testGetAllKulturnePonudebyContentPage() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>>() { };

        ResponseEntity<RestPageImpl<KulturnaPonudaDTO>> responseEntity = restTemplate.exchange("/api/kulturnePonude/filter-by-content-page/g?page=0&size=2", HttpMethod.GET, httpEntity, responseType);

        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FILTERED_BY_CONTENT_NUMBER_OF_ITEMS_PAGE_0, kulturnaPonudaDTOS.size());
        assertEquals(103, (long) kulturnaPonudaDTOS.get(0).getId());
        assertEquals(100, (long) kulturnaPonudaDTOS.get(1).getId());
    }

    @Test
    public void testGetAllKulturnePonudebyContentPage2() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>>() { };

        ResponseEntity<RestPageImpl<KulturnaPonudaDTO>> responseEntity = restTemplate.exchange("/api/kulturnePonude/filter-by-content-page/g?page=1&size=2", HttpMethod.GET, httpEntity, responseType);

        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FILTERED_BY_CONTENT_NUMBER_OF_ITEMS_PAGE_1, kulturnaPonudaDTOS.size());
        assertEquals(101, (long) kulturnaPonudaDTOS.get(0).getId());
    }

    @Test
    public void testGetAllKulturnePonudebyContentPageFail() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>>() { };

        ResponseEntity<RestPageImpl<KulturnaPonudaDTO>> responseEntity = restTemplate.exchange("/api/kulturnePonude/filter-by-content-page/glupost?page=0&size=2", HttpMethod.GET, httpEntity, responseType);

        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, kulturnaPonudaDTOS.size());
    }

    @Test
    public void testGetAllKulturnePonudebyContentPageFail2() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>>() { };

        ResponseEntity<RestPageImpl<KulturnaPonudaDTO>> responseEntity = restTemplate.exchange("/api/kulturnePonude/filter-by-content-page/g?page=2&size=2", HttpMethod.GET, httpEntity, responseType);

        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FILTERED_BY_CONTENT_NUMBER_OF_ITEMS_PAGE_2, kulturnaPonudaDTOS.size());
    }

    @Test
    public void testGetAllKulturnePonudebyLocationPage() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>>() { };

        ResponseEntity<RestPageImpl<KulturnaPonudaDTO>> responseEntity = restTemplate.exchange("/api/kulturnePonude/filter-by-location-page/Novi Sad?page=0&size=2", HttpMethod.GET, httpEntity, responseType);

        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FILTERED_BY_LOCATION_NUMBER_OF_ITEMS_PAGE_0, kulturnaPonudaDTOS.size());
    }

    @Test
    public void testGetAllKulturnePonudebyLocationPage2() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>>() { };

        ResponseEntity<RestPageImpl<KulturnaPonudaDTO>> responseEntity = restTemplate.exchange("/api/kulturnePonude/filter-by-location-page/Novi Sad?page=1&size=2", HttpMethod.GET, httpEntity, responseType);

        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FILTERED_BY_LOCATION_NUMBER_OF_ITEMS_PAGE_1, kulturnaPonudaDTOS.size());
    }

    @Test
    public void testGetAllKulturnePonudebyLocationPageFail() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>>() { };

        ResponseEntity<RestPageImpl<KulturnaPonudaDTO>> responseEntity = restTemplate.exchange("/api/kulturnePonude/filter-by-location-page/Nedodjija?page=0&size=2", HttpMethod.GET, httpEntity, responseType);

        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, kulturnaPonudaDTOS.size());
    }

    @Test
    public void testGetAllKulturnePonudebyLocationPageFail2() {
        login("124@gmail.com", "admin");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);

        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);

        ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<KulturnaPonudaDTO>>() { };

        ResponseEntity<RestPageImpl<KulturnaPonudaDTO>> responseEntity = restTemplate.exchange("/api/kulturnePonude/filter-by-location-page/Novi Sad?page=2&size=2", HttpMethod.GET, httpEntity, responseType);

        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(FILTERED_BY_LOCATION_NUMBER_OF_ITEMS_PAGE_2, kulturnaPonudaDTOS.size());
    }

}
