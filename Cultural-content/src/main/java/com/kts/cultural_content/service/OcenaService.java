package com.kts.cultural_content.service;

import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.repository.OcenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OcenaService implements ServiceInterface<Ocena> {

    @Autowired
    private OcenaRepository oRepository;

    @Override
    public List<Ocena> findAll() {
        return oRepository.findAll();
    }

    @Override
    public Ocena findOne(Integer id) {
        return oRepository.findById(id).orElse( null);
    }

    @Override
    public Ocena create(Ocena entity) throws Exception {
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
