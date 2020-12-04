package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.AdminRepository;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrovaniKorisnikService  implements ServiceInterface<RegistrovaniKorisnik> {
    @Autowired
    private RegistrovaniKorisnikRepository rkRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<RegistrovaniKorisnik> findAll() {
        return rkRepository.findAll();
    }

    public Page<RegistrovaniKorisnik> findAll(Pageable pageable) {
        return rkRepository.findAll(pageable);
    }

    @Override
    public RegistrovaniKorisnik findOne(Integer id) {
        return rkRepository.findById(id).orElse(null);
    }

    @Override
    public RegistrovaniKorisnik create(RegistrovaniKorisnik entity) throws Exception {
        if(rkRepository.findByKorisnickoIme(entity.getKorisnickoIme()) != null ) {
            if(adminRepository.findByKorisnickoIme(entity.getKorisnickoIme()) != null) {
                throw new Exception("Korisnik with given username already exists");
            }
        }
        if(rkRepository.findByEmail(entity.getEmail()) != null) {
            if(adminRepository.findByEmail(entity.getEmail()) != null) {
                throw new Exception("Korisnik with given username already exists");
            }
        }
        return rkRepository.save(entity);
    }

    @Override
    public RegistrovaniKorisnik update(RegistrovaniKorisnik entity, Integer id) throws Exception {
        RegistrovaniKorisnik existingRK = rkRepository.findById(id).orElse(null);
        if(existingRK == null){
            throw new Exception("Registrovani korisnik with given id doesn't exist");
        }
        if(rkRepository.findByKorisnickoIme(entity.getKorisnickoIme()) != null ) {
            if(adminRepository.findByKorisnickoIme(entity.getKorisnickoIme()) != null) {
                throw new Exception("Korisnik with given username already exists");
            }
        }
        existingRK.setKorisnickoIme(entity.getKorisnickoIme());
        existingRK.setIme(entity.getIme());
        existingRK.setPrezime(entity.getPrezime());
        if(rkRepository.findByEmail(entity.getEmail()) != null) {
            if(adminRepository.findByEmail(entity.getEmail()) != null) {
                throw new Exception("Korisnik with given username already exists");
            }
        }
        existingRK.setEmail(entity.getEmail());
        return rkRepository.save(existingRK);
    }

    @Override
    public void delete(Integer id) throws Exception {
        RegistrovaniKorisnik existingRK = rkRepository.findById(id).orElse(null);
        if(existingRK == null){
            throw new Exception("Registrovani korisnik with given id doesn't exist");
        }
        rkRepository.delete(existingRK);
    }

    public RegistrovaniKorisnik save(RegistrovaniKorisnik k) {
        return rkRepository.save(k);
    }

}
