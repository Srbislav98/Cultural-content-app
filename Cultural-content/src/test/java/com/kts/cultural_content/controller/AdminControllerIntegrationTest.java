package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.AdminDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.mapper.RestPageImpl;
import com.kts.cultural_content.service.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class AdminControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    AdminService adminService;

    // JWT token za pristup REST servisima. Bice dobijen pri logovanju
     private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }


    @Test
    public void testGetAllAdmins() {

         login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
         HttpHeaders headers = new HttpHeaders();
         headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
         HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<AdminDTO[]> responseEntity =
                restTemplate.exchange("/api/admins", HttpMethod.GET,httpEntity, AdminDTO[].class);


        AdminDTO[] admini = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, admini.length);
        assertEquals("124@gmail.com", admini[0].getEmail());
    }
    @Test
    public void testGetAllCulturalContentCategoriesPageable() {
        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        //Spring RestTemplate with paginated API
        ParameterizedTypeReference<RestPageImpl<AdminDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<AdminDTO>>() { };
        ResponseEntity<RestPageImpl<AdminDTO>> responseEntity =
                restTemplate.exchange("/api/admins/by-page?page=0&size=2",
                        HttpMethod.GET, httpEntity, responseType);
        List<AdminDTO> admini = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, admini.size());
        assertEquals("124@gmail.com", admini.get(06).getEmail());
    }
}
