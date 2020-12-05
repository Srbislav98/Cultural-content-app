package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.repository.FotografijaRepository;
import com.kts.cultural_content.repository.KomentarRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KomentarService implements ServiceInterface<Komentar> {
    @Autowired
    private KomentarRepository komentarRepository;
    @Autowired
    private KulturnaPonudaRepository kulturnaPonudaRepository;
    @Autowired
    private RegistrovaniKorisnikRepository registrovaniKorisnikRepository;
    @Autowired
    private FotografijaRepository fotografijaRepository;

    @Override
    public List<Komentar> findAll() {
        return komentarRepository.findAll();
    }

    public Page<Komentar> findAll(Pageable pageable) {
        return komentarRepository.findAll(pageable);
    }

    @Override
    public Komentar findOne(Integer id) {
        return komentarRepository.findById(id).orElse( null);
    }

    @Override
    public Komentar create(Komentar entity) throws Exception {
        entity.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        entity.setRegistrovaniKorisnik(registrovaniKorisnikRepository.findById(entity.getRegId()).orElse(null));
        return komentarRepository.save(entity);
    }

    @Override
    public Komentar update(Komentar entity, Integer id) throws Exception {
        Komentar existingkomentar = komentarRepository.findById(id).orElse(null);
        if(existingkomentar == null){
            throw new Exception("komentar with given id doesn't exist");
        }
        existingkomentar.setVrednost(entity.getVrednost());
        existingkomentar.setKulId(entity.getKulId());
        existingkomentar.setRegId(entity.getRegId());
        existingkomentar.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        existingkomentar.setRegistrovaniKorisnik(registrovaniKorisnikRepository.findById(entity.getRegId()).orElse(null));

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
