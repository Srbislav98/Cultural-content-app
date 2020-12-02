package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KomentarService implements ServiceInterface<Komentar> {
    @Autowired
    private KomentarRepository komentarRepository;

    @Override
    public List<Komentar> findAll() {
        return komentarRepository.findAll();
    }

    @Override
    public Komentar findOne(Integer id) {
        return komentarRepository.findById(id).orElse( null);
    }

    @Override
    public Komentar create(Komentar entity) throws Exception {
        return komentarRepository.save(entity);
    }

    @Override
    public Komentar update(Komentar entity, Integer id) throws Exception {
        Komentar existingkomentar = komentarRepository.findById(id).orElse(null);
        if(existingkomentar == null){
            throw new Exception("komentar with given id doesn't exist");
        }
        existingkomentar.setVrednost(entity.getVrednost());
        existingkomentar.setKulturnaPonuda(entity.getKulturnaPonuda());
        existingkomentar.setRegistrovaniKorisnik(entity.getRegistrovaniKorisnik());

        return komentarRepository.save(existingkomentar);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Komentar existingkomentar = komentarRepository.findById(id).orElse(null);
        if (existingkomentar == null){
            throw new Exception("komentar with given id doesn't exist");
        }
        komentarRepository.delete(existingkomentar);
    }

    public Komentar save(Komentar o){
        return komentarRepository.save(o);
    }
}
