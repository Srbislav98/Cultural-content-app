package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.OcenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OcenaService implements ServiceInterface<Ocena> {

    @Autowired
    private OcenaRepository oRepository;
    private KulturnaPonudaRepository kulturnaPonudaRepository;

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
            return new Ocena(-1);
        if (entity.getKulturnaPonuda()!=null) {
            KulturnaPonuda k = kulturnaPonudaRepository.findById(entity.getKulturnaPonuda().getId()).orElse(null);
            if (k != null) {
                k.getOcene().add(entity);
                kulturnaPonudaRepository.save(k);
            }
        }
        return oRepository.save(entity);
    }

    @Override
    public Ocena update(Ocena entity, Integer id) throws Exception {
        Ocena existingOcena = oRepository.findById(id).orElse(null);
        if(existingOcena == null){
            throw new Exception("Ocena with given id doesn't exist");
        }
        existingOcena.setVrednost(entity.getVrednost());
        existingOcena.setKulturnaPonuda(entity.getKulturnaPonuda());
        existingOcena.setRegistrovaniKorisnik(entity.getRegistrovaniKorisnik());

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
