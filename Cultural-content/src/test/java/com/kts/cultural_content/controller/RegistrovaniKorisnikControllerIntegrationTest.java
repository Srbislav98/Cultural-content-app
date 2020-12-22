package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.AdminDTO;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.mapper.RestPageImpl;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.service.AdminService;
import com.kts.cultural_content.service.RegistrovaniKorisnikService;
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
public class RegistrovaniKorisnikControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    RegistrovaniKorisnikService rkService;

    // JWT token za pristup REST servisima. Bice dobijen pri logovanju
    private String accessToken;


    public void login(String username, String password) {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO(username,password), UserTokenStateDTO.class);
        accessToken = "Bearer " + responseEntity.getBody().getAccessToken();
    }


    @Test
    public void testGetAllRegistrovaniKorisnici() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<RegistrovaniKorisnikDTO[]> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici", HttpMethod.GET,httpEntity, RegistrovaniKorisnikDTO[].class);


        RegistrovaniKorisnikDTO[] admini = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, admini.length);
        assertEquals("123@gmail.com", admini[0].getEmail());
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
        ParameterizedTypeReference<RestPageImpl<RegistrovaniKorisnikDTO>> responseType = new ParameterizedTypeReference<RestPageImpl<RegistrovaniKorisnikDTO>>() { };
        ResponseEntity<RestPageImpl<RegistrovaniKorisnikDTO>> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici/by-page?page=0&size=2",
                        HttpMethod.GET, httpEntity, responseType);
        List<RegistrovaniKorisnikDTO> admini = responseEntity.getBody().getContent();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, admini.size());
        assertEquals("123@gmail.com", admini.get(0).getEmail());
    }
    @Test
    public void testGetRegistrovaniKorisnik() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici/1", HttpMethod.GET,httpEntity, RegistrovaniKorisnikDTO.class);


        RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("123@gmail.com", admini.getEmail());
    }
    @Test
    public void testGetRegistrovaniKorisnikNotExisting() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici/50", HttpMethod.GET,httpEntity, RegistrovaniKorisnikDTO.class);


        RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testCreateRegistrovaniKorisnik() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");

        HttpEntity<RegistrovaniKorisnikDTO> httpEntity = new HttpEntity<RegistrovaniKorisnikDTO>(k,headers);
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici", HttpMethod.POST,httpEntity, RegistrovaniKorisnikDTO.class);


        RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("slavik@gmail.com", admini.getEmail());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testCreateRegistrovaniKorisnikEmailExists() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","123@gmail.com","mark12");

        HttpEntity<RegistrovaniKorisnikDTO> httpEntity = new HttpEntity<RegistrovaniKorisnikDTO>(k,headers);
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici", HttpMethod.POST,httpEntity, RegistrovaniKorisnikDTO.class);


        RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        //assertEquals("slavik@gmail.com", admini.getEmail());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testCreateRegistrovaniKorisnikUserNameExists() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","arak","slavk@gmail.com","mark12");

        HttpEntity<RegistrovaniKorisnikDTO> httpEntity = new HttpEntity<RegistrovaniKorisnikDTO>(k,headers);
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici", HttpMethod.POST,httpEntity, RegistrovaniKorisnikDTO.class);


        RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        //assertEquals("slavik@gmail.com", admini.getEmail());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateRegistrovaniKorisnik() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(1,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");

        HttpEntity<RegistrovaniKorisnikDTO> httpEntity = new HttpEntity<RegistrovaniKorisnikDTO>(k,headers);
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici/1", HttpMethod.PUT,httpEntity, RegistrovaniKorisnikDTO.class);


        RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Slavko", admini.getIme());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateRegistrovaniKorisnikIdNotExist() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(1,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");

        HttpEntity<RegistrovaniKorisnikDTO> httpEntity = new HttpEntity<RegistrovaniKorisnikDTO>(k,headers);
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici/51", HttpMethod.PUT,httpEntity, RegistrovaniKorisnikDTO.class);


        RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        //assertEquals("Slavko", admini.getIme());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteRegistrovaniKorisnik() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(1,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");

        HttpEntity<RegistrovaniKorisnikDTO> httpEntity = new HttpEntity<RegistrovaniKorisnikDTO>(headers);
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici/1", HttpMethod.DELETE,httpEntity, RegistrovaniKorisnikDTO.class);


        RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //assertEquals("Slavko", admini.getIme());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteRegistrovaniKorisnikNotFound() {

        login("124@gmail.com", "admin");

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(1,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");

        HttpEntity<RegistrovaniKorisnikDTO> httpEntity = new HttpEntity<RegistrovaniKorisnikDTO>(headers);
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity =
                restTemplate.exchange("/api/registrovaniKorisnici/1011", HttpMethod.DELETE,httpEntity, RegistrovaniKorisnikDTO.class);


        RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        //assertEquals("Slavko", admini.getIme());
    }
}
