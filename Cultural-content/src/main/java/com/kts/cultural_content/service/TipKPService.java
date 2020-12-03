package com.kts.cultural_content.service;

import com.kts.cultural_content.model.TipKulturnePonude;
import com.kts.cultural_content.repository.TipKPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipKPService  implements ServiceInterface<TipKulturnePonude> {
    @Autowired
    private TipKPRepository oRepository;

    @Override
    public List<TipKulturnePonude> findAll() {
        return null;
    }

    @Override
    public TipKulturnePonude findOne(Integer id) {
        return oRepository.findById(id).orElse(null);
    }

    @Override
    public TipKulturnePonude create(TipKulturnePonude entity) throws Exception {
        return oRepository.save(entity);
    }

    @Override
    public TipKulturnePonude update(TipKulturnePonude entity, Integer id) throws Exception {
        TipKulturnePonude existingTipKulturnePonude = oRepository.findById(id).orElse(null);
        if(existingTipKulturnePonude == null){
            throw new Exception("TipKulturnePonude with given id doesn't exist");
        }
        existingTipKulturnePonude.setNaziv(entity.getNaziv());
        existingTipKulturnePonude.setKulturnaPonudas(entity.getKulturnaPonudas());

        return oRepository.save(existingTipKulturnePonude);
    }

    @Override
    public void delete(Integer id) throws Exception {
        TipKulturnePonude existingTipKulturnePonude = oRepository.findById(id).orElse(null);
        if (existingTipKulturnePonude == null){
            throw new Exception("TipKulturnePonude with given id doesn't exist");
        }
        oRepository.delete(existingTipKulturnePonude);
    }

    public TipKulturnePonude save(TipKulturnePonude k){
        return oRepository.save(k);
    }
}