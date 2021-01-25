package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.repository.FotografijaRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.RecenzijaRepository;
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
    private RecenzijaRepository recenzijaRepository;
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
        System.out.println(entity.getKulId());
        entity.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        entity.setRecenzija(recenzijaRepository.findById(entity.getRecId()).orElse(null));
        if(!entity.getLokacijaFajl().equals(""))
            entity.setFoto(new File(entity.getLokacijaFajl()));

        return  fotografijaRepository.save(entity);
    }

    @Override
    public Fotografija update(Fotografija entity, Integer id) throws Exception {
        Fotografija existingFotografija = fotografijaRepository.findById(id).orElse(null);
        if(existingFotografija == null){
            throw new Exception("Fotografija with given id doesn't exist");
        }
        existingFotografija.setRecId(entity.getRecId());
        existingFotografija.setKulId(entity.getKulId());
        existingFotografija.setRecenzija(recenzijaRepository.findById(entity.getRecId()).orElse(null));
        existingFotografija.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        existingFotografija.setLokacijaFajl(entity.getLokacijaFajl());
        if (!entity.getLokacijaFajl().equals(""))
            existingFotografija.setFoto(new File(entity.getLokacijaFajl()));
        else if (entity.getFoto()!=null)
            existingFotografija.setFoto(entity.getFoto());
        else
            existingFotografija.setFoto(null);
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
