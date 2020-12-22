package com.kts.cultural_content.service;

import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.Uloga;
import com.kts.cultural_content.repository.AdminRepository;
import com.kts.cultural_content.repository.UlogaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UlogaService {
    @Autowired
    private UlogaRepository ulogaRepository;

    public List<Uloga> findByname(String role_user) {
        return ulogaRepository.findByIme(role_user);
    }

    public List<Uloga> findById(Integer id) {
        Uloga auth = this.ulogaRepository.getOne(id);
        List<Uloga> auths = new ArrayList<>();
        if (auth == null) {
            return auths;
        }
        auths.add(auth);
        return auths;
    }
}
