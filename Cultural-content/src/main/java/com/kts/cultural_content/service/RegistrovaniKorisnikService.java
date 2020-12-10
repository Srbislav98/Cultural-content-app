package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.model.Uloga;
import com.kts.cultural_content.repository.AdminRepository;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrovaniKorisnikService  implements ServiceInterface<RegistrovaniKorisnik> {
    @Autowired
    private RegistrovaniKorisnikRepository rkRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UlogaService ulogaService;

    @Autowired
    private PasswordEncoder passwordEncoder;


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
        int broj=rkRepository.findAll().size()+adminRepository.findAll().size();
        if(rkRepository.findById(entity.getId()).orElse(null) != null ||
                adminRepository.findById(entity.getId()).orElse(null) != null) {
            for (int i=(broj+1);i>=1;--i){
                if(rkRepository.findById(i).orElse(null) == null &&
                        adminRepository.findById(i).orElse(null) == null) {
                    entity.setId(i);
                    break;
                }
            }
        }
        if(rkRepository.findByKorisnickoIme(entity.getKorisnickoIme()) != null ||
                adminRepository.findByKorisnickoIme(entity.getKorisnickoIme()) != null) {
            throw new Exception("Korisnik with given username already exists");
        }
        if(rkRepository.findByEmail(entity.getEmail()) != null ||
                adminRepository.findByEmail(entity.getEmail()) != null) {
            throw new Exception("Korisnik with given username already exists");
        }
        List<Uloga> auth = ulogaService.findByname("ROLE_USER");
        entity.setLozinka(passwordEncoder.encode(entity.getLozinka()));
        entity.setUloga(auth);
        entity.setEnabled(false);
        return rkRepository.save(entity);
    }

    @Override
    public RegistrovaniKorisnik update(RegistrovaniKorisnik entity, Integer id) throws Exception {
        RegistrovaniKorisnik existingRK = rkRepository.findById(id).orElse(null);
        if(existingRK == null){
            throw new Exception("Registrovani korisnik with given id doesn't exist");
        }
        existingRK.setIme(entity.getIme());
        existingRK.setPrezime(entity.getPrezime());
        existingRK.setLozinka(entity.getLozinka());
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

    public RegistrovaniKorisnik findByEmail(String email) {
        return rkRepository.findByEmail(email);
    }

    public RegistrovaniKorisnik findByKorisnickoIme(String korisnickoIme) {
        return rkRepository.findByKorisnickoIme(korisnickoIme);
    }

    public RegistrovaniKorisnik create(Korisnik user) throws Exception {
        RegistrovaniKorisnik entity=new RegistrovaniKorisnik();
        entity.setEmail(user.getEmail());
        entity.setLozinka(passwordEncoder.encode(user.getLozinka()));
        entity.setId(user.getId());
        entity.setKorisnickoIme(user.getKorisnickoIme());
        entity.setIme(user.getIme());
        entity.setPrezime(user.getPrezime());
        entity.setEnabled(user.getEnabled());

        int broj=rkRepository.findAll().size()+adminRepository.findAll().size();
        if(rkRepository.findById(entity.getId()).orElse(null) != null ||
                adminRepository.findById(entity.getId()).orElse(null) != null) {
            for (int i=(broj+1);i>=1;--i){
                if(rkRepository.findById(i).orElse(null) == null &&
                        adminRepository.findById(i).orElse(null) == null) {
                    entity.setId(i);
                    break;
                }
            }
        }
        if(rkRepository.findByKorisnickoIme(entity.getKorisnickoIme()) != null ||
                adminRepository.findByKorisnickoIme(entity.getKorisnickoIme()) != null) {
            throw new Exception("Korisnik with given username already exists");
        }
        if(rkRepository.findByEmail(entity.getEmail()) != null ||
                adminRepository.findByEmail(entity.getEmail()) != null) {
            throw new Exception("Korisnik with given username already exists");
        }

        List<Uloga> auth = ulogaService.findByname("ROLE_USER");
        entity.setUloga(auth);
        return rkRepository.save(entity);
    }

    public void UpdateResetPassword(String token, String email) throws KorisnikNotFoundException {
        RegistrovaniKorisnik r = findByEmail(email);

        if(r!=null){
            r.setResetPasswordToken(token);
            rkRepository.save(r);
        }else {
            throw new KorisnikNotFoundException("Could not find the customer with the email " + email);
        }

    }

    public RegistrovaniKorisnik get(String resetPasswordToken){
        return rkRepository.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(RegistrovaniKorisnik r, String pass){
        r.setLozinka(passwordEncoder.encode(pass));
        r.setResetPasswordToken(null);

        rkRepository.save(r);
    }
}
