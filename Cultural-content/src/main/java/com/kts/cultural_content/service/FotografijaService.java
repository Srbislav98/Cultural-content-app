package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.repository.FotografijaRepository;
import com.kts.cultural_content.repository.KomentarRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
@Service
public class FotografijaService implements  ServiceInterface<Fotografija> {
    @Autowired
    private FotografijaRepository fotografijaRepository;
    @Autowired
    private KulturnaPonudaRepository kulturnaPonudaRepository;
    @Autowired
    private KomentarRepository komentarRepository;

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
        entity.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        entity.setKomentar(komentarRepository.findById(entity.getKomId()).orElse(null));
        entity.setFoto(new File(entity.getLokacijaFajl()));
        return  fotografijaRepository.save(entity);
    }

    @Override
    public Fotografija update(Fotografija entity, Integer id) throws Exception {
        Fotografija existingFotografija = fotografijaRepository.findById(id).orElse(null);
        if(existingFotografija == null){
            throw new Exception("Fotografija with given id doesn't exist");
        }
        existingFotografija.setKomId(entity.getKomId());
        existingFotografija.setKulId(entity.getKulId());
        existingFotografija.setKomentar(komentarRepository.findById(entity.getKomId()).orElse(null));
        existingFotografija.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        existingFotografija.setLokacijaFajl(entity.getLokacijaFajl());
        existingFotografija.setFoto(new File(entity.getLokacijaFajl()));

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
