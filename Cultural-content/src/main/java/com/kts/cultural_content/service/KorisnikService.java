package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.Uloga;
import com.kts.cultural_content.model.VerificationToken;
import com.kts.cultural_content.repository.KorisnikRepository;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
import com.kts.cultural_content.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikService implements ServiceInterface<Korisnik> {


    @Autowired
    private UlogaService ulogaService;

    @Autowired
    private VerificationTokenRepository vtRepository;

    @Autowired
    private KorisnikRepository kRepository;

    @Override
    public List<Korisnik> findAll() {
        return kRepository.findAll();
    }

    @Override
    public Korisnik findOne(Integer id) {
        return kRepository.findById(id).orElse(null);
    }

    @Override
    public Korisnik create(Korisnik entity) throws Exception {
        List<Uloga> auth = ulogaService.findByname("ROLE_USER");
        entity.setUloga(auth);
        return kRepository.save(entity);
    }

    @Override
    public Korisnik update(Korisnik entity, Integer id) throws Exception {
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    public Korisnik findByEmail(String email) {
        return kRepository.findByEmail(email);
    }

    public Korisnik findByKorisnickoIme(String korisnickoIme) {
        return kRepository.findByKorisnickoIme(korisnickoIme);
    }

    public void createVerificationToken(RegistrovaniKorisnik user, String token) {
        VerificationToken token1=new VerificationToken(token,user);
        vtRepository.save(token1);
    }

    public void save(Korisnik user) {
        kRepository.save(user);
    }
}
