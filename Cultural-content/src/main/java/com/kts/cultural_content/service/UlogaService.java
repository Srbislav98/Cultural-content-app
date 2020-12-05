package com.kts.cultural_content.service;

import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.Uloga;
import com.kts.cultural_content.repository.AdminRepository;
import com.kts.cultural_content.repository.UlogaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UlogaService implements ServiceInterface<Uloga> {
    @Autowired
    private UlogaRepository ulogaRepository;
    @Override
    public List<Uloga> findAll() {
        return null;
    }

    @Override
    public Uloga findOne(Integer id) {
        return null;
    }

    @Override
    public Uloga create(Uloga entity) throws Exception {
        return null;
    }

    @Override
    public Uloga update(Uloga entity, Integer id) throws Exception {
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    public List<Uloga> findByname(String role_user) {
        return ulogaRepository.findByIme(role_user);
    }
}
