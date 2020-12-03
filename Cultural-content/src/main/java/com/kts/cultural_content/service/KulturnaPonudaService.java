package com.kts.cultural_content.service;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class KulturnaPonudaService implements ServiceInterface<KulturnaPonuda> {
    @Autowired
    private KulturnaPonudaRepository oRepository;

    @Override
    public List<KulturnaPonuda> findAll() {
        return null;
    }

    @Override
    public KulturnaPonuda findOne(Integer id) {
        return oRepository.findById(id).orElse(null);
    }

    @Override
    public KulturnaPonuda create(KulturnaPonuda entity) throws Exception {
        return oRepository.save(entity);
    }

    @Override
    public KulturnaPonuda update(KulturnaPonuda entity, Integer id) throws Exception {
        KulturnaPonuda existingKulturnaPonuda = oRepository.findById(id).orElse(null);
        if(existingKulturnaPonuda == null){
            throw new Exception("KulturnaPonuda with given id doesn't exist");
        }
        existingKulturnaPonuda.setNaziv(entity.getNaziv());
        existingKulturnaPonuda.setAdmin(entity.getAdmin());
        existingKulturnaPonuda.setTipKulturnePonude(entity.getTipKulturnePonude());
        existingKulturnaPonuda.setAdresa(entity.getAdresa());
        existingKulturnaPonuda.setFotogrfija(entity.getFotogrfija());
        existingKulturnaPonuda.setGeoDuzina(entity.getGeoDuzina());
        existingKulturnaPonuda.setGeoSirina(entity.getGeoSirina());
        existingKulturnaPonuda.setOpis(entity.getOpis());
        existingKulturnaPonuda.setRegistrovaniKorisnik(entity.getRegistrovaniKorisnik());

        return oRepository.save(existingKulturnaPonuda);
    }

    @Override
    public void delete(Integer id) throws Exception {
        KulturnaPonuda existingKulturnaPonuda = oRepository.findById(id).orElse(null);
        if (existingKulturnaPonuda == null){
            throw new Exception("KulturnaPonuda with given id doesn't exist");
        }
        oRepository.delete(existingKulturnaPonuda);
    }

    public KulturnaPonuda save(KulturnaPonuda k){
        return oRepository.save(k);
    }
}
