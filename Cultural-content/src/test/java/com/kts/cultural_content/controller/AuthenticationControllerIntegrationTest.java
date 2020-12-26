package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.service.RegistrovaniKorisnikService;
import com.kts.cultural_content.service.VerificationTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class AuthenticationControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    RegistrovaniKorisnikService rkService;
    @Autowired
    VerificationTokenService vtService;

    @Test
    @Transactional
    @Rollback(true)
    public void testLogIn() {
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO("124@gmail.com", "admin"), UserTokenStateDTO.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
    @Test
    @Transactional
    @Rollback(true)
    public void testLogInUnauthorized() {
        ResponseEntity<?> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO("12324@gmail.com", "admin"),null);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());

    }
    @Test
    public void testSignUp() throws Exception {
        RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(53,"David","Slavic","slavsk","slavsk@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity = restTemplate.postForEntity("/auth/sign-up",
                k, RegistrovaniKorisnikDTO.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("slavsk" ,responseEntity.getBody().getKorisnickoIme());
        vtService.findByUserId(53);
        vtService.deleteWithUserId(53);
        rkService.delete(53);
        //System.out.println(rkService.findAll()+"///sadss//////");

    }
    @Test
    @Transactional
    @Rollback(true)
    public void testSignUpEmailExists() {
        RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(53,"David","Slavic","slavsk","123@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity = restTemplate.postForEntity("/auth/sign-up",
                k, RegistrovaniKorisnikDTO.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testSignUpUsernameExists() throws Exception {
        RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(53,"David","Slavic","arak","123eee@gmail.com","mark12");
        ResponseEntity<RegistrovaniKorisnikDTO> responseEntity = restTemplate.postForEntity("/auth/sign-up",
                k, RegistrovaniKorisnikDTO.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testChangePassword(){
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO("123@gmail.com", "user"), UserTokenStateDTO.class);
        String accessToken = "Bearer " + responseEntity.getBody().getAccessToken();

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(1,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        AuthenticationController.PasswordChanger pc=new AuthenticationController.PasswordChanger();
        pc.oldPassword="user";
        pc.newPassword="aser";
        HttpEntity<AuthenticationController.PasswordChanger> httpEntity = new HttpEntity<AuthenticationController.PasswordChanger>( pc,headers);
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<?> responseEntity2 =
                restTemplate.exchange("/auth/change-password", HttpMethod.POST,httpEntity,Void.class);

        //RegistrovaniKorisnikDTO admini = responseEntity.getBody();
        assertEquals(HttpStatus.ACCEPTED, responseEntity2.getStatusCode());
        //assertEquals("Slavko", admini.getIme());
    }
    @Test
    @Transactional
    //@Rollback(true)
    public void testChangePasswordWrongOldPassw(){
        ResponseEntity<UserTokenStateDTO> responseEntity = restTemplate.postForEntity("/auth/log-in",
                new UserLoginDTO("124@gmail.com", "admin"), UserTokenStateDTO.class);
        String accessToken = "Bearer " + responseEntity.getBody().getAccessToken();

        // postavimo JWT token u zaglavlje zahteva da bi bilo dozvoljeno da pozovemo funkcionalnost
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", accessToken);
        // kreiramo objekat koji saljemo u sklopu zahteva
        // objekat nema telo, vec samo zaglavlje, jer je rec o GET zahtevu
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(1,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        AuthenticationController.PasswordChanger pc=new AuthenticationController.PasswordChanger();
        pc.oldPassword="usmerer";
        pc.newPassword="aser";
        HttpEntity<AuthenticationController.PasswordChanger> httpEntity = new HttpEntity<AuthenticationController.PasswordChanger>( pc,headers);
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<?> responseEntity2 =
                restTemplate.exchange("/auth/change-password", HttpMethod.POST,httpEntity,Void.class);

        //RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity2.getStatusCode());
        //assertEquals("Slavko", admini.getIme());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testConfirmRegistration(){
        String token="abb";
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<?> responseEntity2 =
                restTemplate.getForEntity("/auth/regitrationConfirm/"+token,Void.class);

        //RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        //assertEquals("Slavko", admini.getIme());
    }
    @Test
    @Transactional
    @Rollback(true)
    public void testConfirmRegistrationWrongToken(){
        String token="wred";
        //RegistrovaniKorisnikDTO k=new RegistrovaniKorisnikDTO(52,"Slavko","Slavkovic","slavik","slavik@gmail.com","mark12");
        ResponseEntity<?> responseEntity2 =
                restTemplate.getForEntity("/auth/regitrationConfirm/"+token,Void.class);

        //RegistrovaniKorisnikDTO admini = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        //assertEquals("Slavko", admini.getIme());
    }
}
