package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.dto.UserTokenStateDTO;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.TipKPRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class ForgotPasswordControllerIntegrationTest {
    @Autowired
    KulturnaPonudaRepository oKulturnaPonuda;

    @Autowired
    TipKPRepository oTip;

    @Autowired
    TestRestTemplate restTemplate;

    public String reset = "";

    @Test
    public void testProcesForgotPassword() {
        String accessToken;
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Object> httpEntity = new HttpEntity<Object>("123@gmail.com", headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange("/recover/password", HttpMethod.POST,httpEntity, String.class);


        String str = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println(str);
        reset = str;
        assertTrue(str.length() > 0);


        httpEntity = new HttpEntity<Object>("N0V4_L0Z1NK4", headers);
        responseEntity = restTemplate.exchange("/recover/reset_password/" + reset, HttpMethod.POST,httpEntity, String.class);

        System.out.println("/recover/reset_password/" + reset);

        str = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println(str);
        assertEquals("Password changed successfully", str);

    }

    /*@Test
    public void testzaChangePassword() {
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Object> httpEntity = new HttpEntity<Object>("N0V4_L0Z1NK4", headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange("/recover/reset_password/" + reset, HttpMethod.POST,httpEntity, String.class);

        System.out.println("/recover/reset_password/" + reset);

        String str = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println(str);
        assertTrue(str.length() > 0);
    }*/
}
