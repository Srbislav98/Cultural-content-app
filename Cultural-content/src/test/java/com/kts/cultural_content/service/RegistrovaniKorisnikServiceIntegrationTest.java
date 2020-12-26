package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class RegistrovaniKorisnikServiceIntegrationTest {

    @Autowired
    RegistrovaniKorisnikService rkService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testFindAll() {
        List<RegistrovaniKorisnik> found = rkService.findAll();
        assertEquals(2, found.size());
    }
    @Test
    public void testFindAllPageable() {
        Pageable pageable = PageRequest.of(0,3);
        Page<RegistrovaniKorisnik> found = rkService.findAll(pageable);
        assertEquals(2, found.getNumberOfElements());
    }

    @Test
    public void testFindOne() {
        RegistrovaniKorisnik found = rkService.findOne(4);
        Long l = 4L;
        assertEquals(l, Long.valueOf(found.getId()));
    }
    @Test
    public void testCreatewithRK() throws Exception {
        RegistrovaniKorisnik k = new RegistrovaniKorisnik(27, "Marko", "Markovic", "Markec2", "marko2@gmail.com", "mark12");
        RegistrovaniKorisnik created = rkService.create(k);
        assertEquals(k.getEmail(), created.getEmail());
        rkService.delete(k.getId());

    }
    @Test(expected = Exception.class)
    public void testCreatewithRKEmailExists() throws Exception {
        RegistrovaniKorisnik k = new RegistrovaniKorisnik(27, "Marko", "Markovic", "Markec2", "5@gmail.com", "mark12");
        RegistrovaniKorisnik created = rkService.create(k);
        assertEquals(k.getEmail(), created.getEmail());
        rkService.delete(k.getId());

    }
    @Test(expected = Exception.class)
    public void testCreatewithRKUsernameExists() throws Exception {
        RegistrovaniKorisnik k = new RegistrovaniKorisnik(27, "Marko", "Markovic", "mark", "5marko@gmail.com", "mark12");
        RegistrovaniKorisnik created = rkService.create(k);
        assertEquals(k.getEmail(), created.getEmail());
        rkService.delete(k.getId());

    }
    @Test

    public void testCreate() throws Exception {
        Korisnik k = new Korisnik(27, "Marko", "Markovic", "Markec2", "marko2@gmail.com", "mark12");
        RegistrovaniKorisnik created = rkService.create(k);
        assertEquals(k.getEmail(), created.getEmail());
        rkService.delete(k.getId());

    }
    @Test(expected = Exception.class)
    public void testCreateEmailExists() throws Exception {
        Korisnik k = new Korisnik(27, "Marko", "Markovic", "Markec2", "5@gmail.com", "mark12");
        RegistrovaniKorisnik created = rkService.create(k);
        assertEquals(k.getEmail(), created.getEmail());
        rkService.delete(k.getId());

    }
    @Test(expected = Exception.class)
    public void testCreateUsernameExists() throws Exception {
        Korisnik k = new Korisnik(27, "Marko", "Markovic", "mark", "5marko@gmail.com", "mark12");
        RegistrovaniKorisnik created = rkService.create(k);
        assertEquals(k.getEmail(), created.getEmail());
        rkService.delete(k.getId());

    }
    @Test
    @Transactional
    @Rollback
    public void testUpdate() throws Exception {
        RegistrovaniKorisnik k=new RegistrovaniKorisnik(22,"Mirko","Mirkovic","mark","mirko123@gmail.com","mark12");
        RegistrovaniKorisnik updated=rkService.update(k,4);
        assertEquals(k.getPrezime(),updated.getPrezime());
    }
    @Test(expected = Exception.class)
    public void testUpdateIdDoesntExists() throws Exception {
        RegistrovaniKorisnik k=new RegistrovaniKorisnik(22,"Mirko","Mirkovic","Mirkec123","mirko123@gmail.com","mark12");
        RegistrovaniKorisnik updated=rkService.update(k,2);
        assertEquals(k.getEmail(),updated.getEmail());
    }

    @Test
    @Transactional
    @Rollback
    public void testDelete() throws Exception {
        //da sacuvamo korisnika da bi ga kasnije vratili
        RegistrovaniKorisnik k = rkService.findOne(1);
        rkService.delete(1);
        assertNull(rkService.findOne(1));
        //ako prodje sve znaci da je ok, vracamo admina
        rkService.create(k);

    }
    @Test
    public void testFindByEmail() {
        RegistrovaniKorisnik found = rkService.findByEmail("5@gmail.com");
        assertEquals("5@gmail.com",found.getEmail());
    }
    @Test
    public void testFindByEmailNull() {
        RegistrovaniKorisnik found = rkService.findByEmail("33@gmail.com");
        assertNull(found);
    }
    @Test
    public void testFindByKorisnickoIme() {
        RegistrovaniKorisnik found = rkService.findByKorisnickoIme("mark");
        assertEquals("mark",found.getKorisnickoIme());
    }
    @Test
    public void testFindByKorisnickoImeNull() {
        RegistrovaniKorisnik found = rkService.findByKorisnickoIme("amika");
        assertNull(found);
    }
    @Test
    @Transactional
    @Rollback
    public void testSave(){
        RegistrovaniKorisnik found = rkService.findByKorisnickoIme("mark");
        found.setPrezime("Volkov");
        rkService.save(found);
        Korisnik foundAgain = rkService.findByKorisnickoIme("mark");
        assertEquals("Volkov",foundAgain.getPrezime());


    }
    @Test
    @Transactional
    @Rollback
    public void testUpdateResetPassword()  {
        try {
            rkService.UpdateResetPassword("axyz","5@gmail.com");
        } catch (KorisnikNotFoundException e) {
            e.printStackTrace();
        }
        RegistrovaniKorisnik found = rkService.findByEmail("5@gmail.com");
        assertEquals("axyz",found.getResetPasswordToken());
    }
    @Test
    public void testUpdateResetPasswordKorisnikNotFound()  {
        try {
            rkService.UpdateResetPassword("axyz","55@gmail.com");
        } catch (KorisnikNotFoundException e) {
            assertEquals("Could not find the customer with the email 55@gmail.com", e.getMessage());
        }
    }
    @Test
    public void testGetKorisnikwithResetPaswordToken(){
        RegistrovaniKorisnik rk1=rkService.findOne(4);
        RegistrovaniKorisnik rk=rkService.get("abc");
        assertEquals(rk.getResetPasswordToken(),"abc");
    }
    @Test
    @Transactional
    @Rollback
    public void testUpdatePasword() throws InterruptedException {
        RegistrovaniKorisnik rk=rkService.findOne(1);
        Date t=new Date();
        Thread.sleep(1000);
        rkService.updatePassword(rk,"abc");
        RegistrovaniKorisnik updated=rkService.findOne(1);
        assertTrue(updated.getLastPasswordResetDate().after(t));
    }
    @Test
    public void testSubscribe() throws Exception {
        RegistrovaniKorisnik rk=rkService.findOne(1);
        int subskrajbovanoPrije=rk.getKulturnaPonuda().size();
        rkService.subscribe(1,101);
        rk=rkService.findOne(1);
        int subskrajbovanoPoslije=rk.getKulturnaPonuda().size();
        assertEquals(subskrajbovanoPrije+1,subskrajbovanoPoslije);
        rkService.unsubscribe(1,101);
    }
    @Test
    public void testUnsubscribe() throws Exception {
        RegistrovaniKorisnik rk=rkService.findOne(1);
        int subskrajbovanoPrije=rk.getKulturnaPonuda().size();
        rkService.unsubscribe(1,100);
        rk=rkService.findOne(1);
        int subskrajbovanoPoslije=rk.getKulturnaPonuda().size();
        assertEquals(subskrajbovanoPrije-1,subskrajbovanoPoslije);
        rkService.subscribe(1,100);
    }
}
