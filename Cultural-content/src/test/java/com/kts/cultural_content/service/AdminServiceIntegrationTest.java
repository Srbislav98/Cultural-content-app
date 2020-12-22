package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.repository.AdminRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class AdminServiceIntegrationTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void testFindAll() {
        List<Admin> found =adminService.findAll();
        assertEquals(1,found.size());
    }

    @Test
    public void testFindAllPageable() {
        Pageable pageable = PageRequest.of(0,2);
        Page<Admin> found = adminService.findAll(pageable);
        assertEquals(1, found.getNumberOfElements());
    }

    @Test
    public void testFindOne() {
        Admin found = adminService.findOne(2);
        Long l=2L;
        assertEquals(l,Long.valueOf(found.getId()));
    }

    @Test
    public void testCreate() throws Exception {
        Admin admin=new Admin(7,"Marko","Markovic","Markec","marko@gmail.com","mark12");
        Admin created=adminService.create(admin);
        assertEquals(admin.getEmail(),created.getEmail());
        adminService.delete(admin.getId());

    }
    @Test
    public void testUpdate() throws Exception {
        Admin admin=new Admin(2,"Mirko","Mirkovic","Mirkec","mirko@gmail.com","mark12");
        Admin updated=adminService.update(admin,2);
        assertEquals(admin.getEmail(),updated.getEmail());
    }

    @Test
    public void testDelete() throws Exception {
        //da sacuvamo admina da bi ga kasnije vratili
        Admin admin = adminService.findOne(2);
        adminService.delete(2);
        assertNull(adminService.findOne(2));
        //ako prodje sve znaci da je ok, vracamo admina
        adminService.create(admin);
    }
}
