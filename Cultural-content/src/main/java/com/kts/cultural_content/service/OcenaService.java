package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.OcenaRepository;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OcenaService implements ServiceInterface<Ocena> {

    @Autowired
    private OcenaRepository oRepository;
    @Autowired
    private KulturnaPonudaRepository kulturnaPonudaRepository;
    @Autowired
    private RegistrovaniKorisnikRepository registrovaniKorisnikRepository;

    @Override
    public List<Ocena> findAll() {
        return oRepository.findAll();
    }

    public Page<Ocena> findAll(Pageable pageable) {
        return oRepository.findAll(pageable);
    }

    @Override
    public Ocena findOne(Integer id) {
        return oRepository.findById(id).orElse( null);
    }

    @Override
    public Ocena create(Ocena entity) throws Exception {
        if (entity.getVrednost()<1 || entity.getVrednost()>5)
            return null;
        //entity.setKulturnaPonuda(kulturnaPonudaRepository.findById(100).orElse(null));
        entity.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        entity.setRegistrovaniKorisnik(registrovaniKorisnikRepository.findById(entity.getRegId()).orElse(null));
        /*if (entity.getKulturnaPonuda()!=null) {
            KulturnaPonuda k = kulturnaPonudaRepository.findById(entity.getKulturnaPonuda().getId()).orElse(null);
            if (k != null) {
                k.getOcene().add(entity);
                kulturnaPonudaRepository.save(k);
            }
        }*/
        //Ocena ocena = oRepository.save(entity);
        return oRepository.save(entity);
    }

    @Override
    public Ocena update(Ocena entity, Integer id) throws Exception {
        Ocena existingOcena = oRepository.findById(id).orElse(null);
        if(existingOcena == null){
            throw new Exception("Ocena with given id doesn't exist");
        }
        if (entity.getVrednost()<1 || entity.getVrednost()>5)
            return null;
        existingOcena.setVrednost(entity.getVrednost());
        existingOcena.setKulId(entity.getKulId());
        existingOcena.setRegId(entity.getRegId());
        existingOcena.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        existingOcena.setRegistrovaniKorisnik(registrovaniKorisnikRepository.findById(entity.getRegId()).orElse(null));

        return oRepository.save(existingOcena);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Ocena existingOcena = oRepository.findById(id).orElse(null);
        if (existingOcena == null){
            throw new Exception("Ocena with given id doesn't exist");
        }
        oRepository.delete(existingOcena);
    }

    public Ocena save(Ocena o){
        return oRepository.save(o);
    }

}
