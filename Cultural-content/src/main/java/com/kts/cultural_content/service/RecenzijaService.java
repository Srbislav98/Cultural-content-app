package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Lokacija;
import com.kts.cultural_content.model.Recenzija;
import com.kts.cultural_content.repository.FotografijaRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.RecenzijaRepository;
import com.kts.cultural_content.repository.RegistrovaniKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecenzijaService implements ServiceInterface<Recenzija> {
    @Autowired
    private RecenzijaRepository recenzijaRepository;
    @Autowired
    private KulturnaPonudaRepository kulturnaPonudaRepository;
    @Autowired
    private RegistrovaniKorisnikRepository registrovaniKorisnikRepository;
    @Autowired
    private FotografijaRepository fotografijaRepository;

    @Override
    public List<Recenzija> findAll() {
        return recenzijaRepository.findAll();
    }

    public Page<Recenzija> findAll(Pageable pageable) {
        return recenzijaRepository.findAll(pageable);
    }

    @Override
    public Recenzija findOne(Integer id) {
        return recenzijaRepository.findById(id).orElse( null);
    }

    @Override
    public Recenzija create(Recenzija entity) throws Exception {

        entity.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        entity.setRegistrovaniKorisnik(registrovaniKorisnikRepository.findById(entity.getRegId()).orElse(null));
        if(entity.getFoto()!=null) {
            entity.setFotogrfija(new Fotografija(0, "",entity.getFoto(),entity.getKulturnaPonuda().getId(), entity.getRegistrovaniKorisnik().getId()));

        }
        return recenzijaRepository.save(entity);
    }

    @Override
    public Recenzija update(Recenzija entity, Integer id) throws Exception {
        Recenzija existingrecenzija = recenzijaRepository.findById(id).orElse(null);
        if(existingrecenzija == null){
            throw new Exception("komentar with given id doesn't exist");
        }
        existingrecenzija.setFoto(entity.getFoto());
        if(entity.getFoto()!=null) {
            try {
                existingrecenzija.getFotogrfija().setFoto(entity.getFoto());
            } catch (NullPointerException e) {
                existingrecenzija.setFotogrfija(new Fotografija(0, "",entity.getFoto(),entity.getKulturnaPonuda().getId(), entity.getRegistrovaniKorisnik().getId()));
                //existingrecenzija.getFotogrfija().setFoto(new File(entity.getFoto()));
            }

        }
        existingrecenzija.setKulId(entity.getKulId());
        existingrecenzija.setRegId(entity.getRegId());
        existingrecenzija.setKulturnaPonuda(kulturnaPonudaRepository.findById(entity.getKulId()).orElse(null));
        existingrecenzija.setRegistrovaniKorisnik(registrovaniKorisnikRepository.findById(entity.getRegId()).orElse(null));
        existingrecenzija.setOcena(entity.getOcena());
        existingrecenzija.setKomentar(entity.getKomentar());

        return recenzijaRepository.save(existingrecenzija);

    }

    @Override
    public void delete(Integer id){
        recenzijaRepository.delete(recenzijaRepository.findById(id).orElse(null));
    }

    public List<Recenzija> filterByGrade(Integer content, Integer id) {
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaRepository.findById(id).orElse(null);
        List<Recenzija> lista = new ArrayList<>();
        assert kulturnaPonuda != null;
        for(Recenzija recenzija: kulturnaPonuda.getRecenzije()){
            if(recenzija.getOcena()==content){
                lista.add(recenzija);
            }
        }
        return lista;
    }
}
