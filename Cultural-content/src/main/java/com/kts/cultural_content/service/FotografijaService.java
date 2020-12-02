package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.repository.FotografijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FotografijaService implements  ServiceInterface<Fotografija> {
    @Autowired
    private FotografijaRepository fotografijaRepository;

    @Override
    public List<Fotografija> findAll() {
        return fotografijaRepository.findAll();
    }

    @Override
    public Fotografija findOne(Integer id) {
        return fotografijaRepository.findById(id).orElse(null);
    }

    @Override
    public Fotografija create(Fotografija entity) throws Exception {
        return  fotografijaRepository.save(entity);
    }

    @Override
    public Fotografija update(Fotografija entity, Integer id) throws Exception {
        Fotografija existingFotografija = fotografijaRepository.findById(id).orElse(null);
        if(existingFotografija == null){
            throw new Exception("Fotografija with given id doesn't exist");
        }
        existingFotografija.setKomentar(entity.getKomentar());
        existingFotografija.setKulturnaPonuda(entity.getKulturnaPonuda());
        existingFotografija.setLokacijaFajl(entity.getLokacijaFajl());
        existingFotografija.setNaziv(entity.getNaziv());

        return fotografijaRepository.save(existingFotografija);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Fotografija existingFotografija = fotografijaRepository.findById(id).orElse(null);
        if (existingFotografija == null){
            throw new Exception("Fotografija with given id doesn't exist");
        }
        fotografijaRepository.delete(existingFotografija);
    }
}
