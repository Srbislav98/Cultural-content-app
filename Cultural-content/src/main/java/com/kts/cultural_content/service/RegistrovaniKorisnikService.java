package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrovaniKorisnikService  implements ServiceInterface<RegistrovaniKorisnik> {
    @Autowired
    private RegistrovaniKorisnikRepository rkRepository;

    @Override
    public List<RegistrovaniKorisnik> findAll() {
        return null;
    }

    @Override
    public RegistrovaniKorisnik findOne(Integer id) {
        return null;
    }

    @Override
    public RegistrovaniKorisnik create(RegistrovaniKorisnik entity) throws Exception {
        return null;
    }

    @Override
    public RegistrovaniKorisnik update(RegistrovaniKorisnik entity, Integer id) throws Exception {
        return null;
    }

    @Override
    public void delete(Integer id) throws Exception {

    }

    public RegistrovaniKorisnik save(RegistrovaniKorisnik k) {
        return rkRepository.save(k);
    }

    public void addRegistrovanKorisnik(RegistrovaniKorisnikDTO rk) {
        RegistrovaniKorisnik k=new RegistrovaniKorisnik(rk);
        rkRepository.save(k);
    }
}
