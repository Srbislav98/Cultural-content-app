package com.kts.cultural_content.service;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Novost;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.NovostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovostService  implements ServiceInterface<Novost> {

    @Autowired
    private NovostRepository novostRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private KulturnaPonudaRepository kulturnaPonudaRepository;

    @Override
    public List<Novost> findAll() {
        return novostRepository.findAll();
    }

    public Page<Novost> findAll(Pageable pageable) {
        return novostRepository.findAll(pageable);
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
        entity.setKulturnaPonuda(kulturnaPonudaRepository.getOne(100));
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

    @Async
    public void obavestenjeNaEmail(Novost entity){

        KulturnaPonuda kulturnaPonuda = entity.getKulturnaPonuda();

        for(RegistrovaniKorisnik registrovaniKorisnik : kulturnaPonuda.getRegistrovaniKorisnik()) {
            String recipientAddress = registrovaniKorisnik.getEmail();
            String subject = "Kulturna ponuda :"+kulturnaPonuda.getNaziv();
            String message = entity.getOpis()+"\n"+entity.getDatum();
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message);
            mailSender.send(email);
        }
    }

    public Novost createSaKulturnomPonudom(Novost entity, Integer id) throws Exception {
        if(novostRepository.findByNaziv(entity.getNaziv()) != null) {
            throw new Exception("Novost with given naziv already exists");
        }
        entity.setKulturnaPonuda(kulturnaPonudaRepository.getOne(id));
        return novostRepository.save(entity);
    }
}
