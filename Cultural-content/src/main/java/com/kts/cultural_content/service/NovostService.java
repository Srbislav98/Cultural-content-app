package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Novost;
import com.kts.cultural_content.repository.NovostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovostService  implements ServiceInterface<Novost> {

    @Autowired
    private NovostRepository novostRepository;

    @Override
    public List<Novost> findAll() {
        return novostRepository.findAll();
    }

    @Override
    public Novost findOne(Integer id) {
        return novostRepository.findById(id).orElse(null);
    }

    @Override
    public Novost create(Novost entity) throws Exception {
        if(novostRepository.findByNaziv(entity.getNaziv()) != null) {
            throw new Exception("Novost with given naziv already exists");
        }
        return novostRepository.save(entity);
    }

    @Override
    public Novost update(Novost entity, Integer id) throws Exception {
        Novost existingNovost =  novostRepository.findById(id).orElse(null);
        if(existingNovost== null){
            throw new Exception("Novost with given id doesn't exist");
        }
        existingNovost.setNaziv(entity.getNaziv());
        existingNovost.setDatum(entity.getDatum());
        existingNovost.setOpis(entity.getOpis());
        if(novostRepository.findByNazivAndIdNot(existingNovost.getNaziv(), id) != null){
            throw new Exception("Novost with given name already exists");
        }
        return novostRepository.save(existingNovost);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Novost existingNovost =  novostRepository.findById(id).orElse(null);
        if(existingNovost == null){
            throw new Exception("Novost with given id doesn't exist");
        }
        novostRepository.delete(existingNovost);
    }
}
