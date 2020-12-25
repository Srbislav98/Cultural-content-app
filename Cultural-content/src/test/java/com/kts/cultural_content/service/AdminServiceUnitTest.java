package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Uloga;
import com.kts.cultural_content.repository.AdminRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;

import static com.kts.cultural_content.constants.KorisnikConstants.*;
import static org.mockito.BDDMockito.given;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class AdminServiceUnitTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private AdminRepository adminRepository;

    //private Admin adminCreate;//=new Admin(NEW_USER_ID,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_USERNAME,NEW_USER_EMAIL,NEW_USER_PASSWORD);
    //private Admin adminExists;
    @Before
    public void setup() {
        Admin adminCreate=new Admin(NEW_ADMIN_ID,NEW_ADMIN_NAME,NEW_ADMIN_SURNAME,NEW_ADMIN_USERNAME,NEW_ADMIN_EMAIL,NEW_ADMIN_PASSWORD);
        Admin adminExists=new Admin(ADMIN_ID,ADMIN_NAME,ADMIN_SURNAME,ADMIN_USERNAME,ADMIN_EMAIL,ADMIN_PASSWORD);
        //admin.setKulturnaPonude(new HashSet<KulturnaPonuda>());
        //admin.setUloga(new ArrayList<Uloga>());
        //admin.setLastPasswordResetDate(new Timestamp(0));
        //System.out.println(admin.getLastPasswordResetDate());
        given(adminRepository.findByKorisnickoIme(NEW_ADMIN_USERNAME)).willReturn(null);
        given(adminRepository.save(adminCreate)).willReturn(adminCreate);
        given(adminRepository.findByKorisnickoIme(ADMIN_USERNAME)).willReturn(adminExists);
        given(adminRepository.findById(ADMIN_ID)).willReturn(Optional.ofNullable(adminExists));
        given(adminRepository.findByKorisnickoImeAndIdNot(ADMIN_USERNAME,ADMIN_ID)).willReturn(null);
        given(adminRepository.save(adminExists)).willReturn(adminExists);
    }

    @Test
    public void testCreate() throws Exception {
        //Admin admin=new Admin(NEW_USER_ID,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_USERNAME,NEW_USER_EMAIL,NEW_USER_PASSWORD);
        Admin adminCreate=new Admin(NEW_ADMIN_ID,NEW_ADMIN_NAME,NEW_ADMIN_SURNAME,NEW_ADMIN_USERNAME,NEW_ADMIN_EMAIL,NEW_ADMIN_PASSWORD);
        Admin created=adminService.create(adminCreate);
        verify(adminRepository, times(1)).findByKorisnickoIme(NEW_ADMIN_USERNAME);
        verify(adminRepository, times(1)).save(adminCreate);

        assertEquals(NEW_ADMIN_USERNAME, created.getKorisnickoIme());
    }
    @Test//(expected = Exception.class)
    public void testCreateUserNameExists() throws Exception {
        //Admin admin=new Admin(NEW_USER_ID,NEW_USER_NAME,NEW_USER_SURNAME,NEW_USER_USERNAME,NEW_USER_EMAIL,NEW_USER_PASSWORD);
       Admin adminExists=new Admin(ADMIN_ID,ADMIN_NAME,ADMIN_SURNAME,ADMIN_USERNAME,ADMIN_EMAIL,ADMIN_PASSWORD);

        try {
            Admin created = adminService.create(adminExists);
        }catch (Exception ex){
            assertEquals("Admin with given username already exists",ex.getMessage());
        }
        verify(adminRepository, times(1)).findByKorisnickoIme(ADMIN_USERNAME);
       // verify(adminRepository, times(1)).save(adminCreate);
       // assertEquals(null, created);
    }
    @Test
    public void testUpdate() throws Exception {
        Admin adminExists=new Admin(ADMIN_ID,ADMIN_NAME,ADMIN_SURNAME,ADMIN_USERNAME,ADMIN_EMAIL,ADMIN_PASSWORD);
        Admin created=adminService.update(adminExists,2);
        verify(adminRepository, times(1)).findById(ADMIN_ID);
        verify(adminRepository, times(1)).findByKorisnickoImeAndIdNot(ADMIN_USERNAME,ADMIN_ID);

        assertEquals(ADMIN_USERNAME, created.getKorisnickoIme());
    }
    @Test
    public void testDelete() throws Exception {
        adminService.delete(2);
        verify(adminRepository, times(1)).findById(ADMIN_ID);
    }
}
