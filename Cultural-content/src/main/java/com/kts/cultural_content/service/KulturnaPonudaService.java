package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Lokacija;
import com.kts.cultural_content.model.TipKulturnePonude;
import com.kts.cultural_content.repository.AdminRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
import com.kts.cultural_content.repository.LokacijaRepository;
import com.kts.cultural_content.repository.TipKPRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class KulturnaPonudaService implements ServiceInterface<KulturnaPonuda> {
    @Autowired
    private KulturnaPonudaRepository oRepository;

    @Autowired
    private AdminRepository oAdmin;

    @Autowired
    private TipKPRepository oTip;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Override
    public List<KulturnaPonuda> findAll() {
        return oRepository.findAll();
    }

    @Override
    public KulturnaPonuda findOne(Integer id) {
        return oRepository.findById(id).orElse(null);
    }

    @Override
    public KulturnaPonuda create(KulturnaPonuda entity) throws Exception {
        Object admin = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(admin instanceof Admin)){
            throw new Exception();
        }
        int id = ((Admin) admin).getId();
        Admin ad = oAdmin.getOne(id);
        entity.setAdmin(ad);

        TipKulturnePonude tip = oTip.getOne(Integer.parseInt(entity.getTipKulturnePonude().getNaziv()));
        entity.setTipKulturnePonude(tip);

        Lokacija lokacija = lokacijaRepository.getOne(Integer.parseInt(entity.getLokacija().getNazivLokacije()));
        entity.setLokacija(lokacija);

        System.out.println("####################################################");
        System.out.println("Upisuje u bazu : ");
        System.out.println("\tID - " + entity.getId());
        System.out.println("\tNaziv - " + entity.getNaziv());
        System.out.println("\tTip KP - " + entity.getTipKulturnePonude().getNaziv() + "; ID - " + entity.getTipKulturnePonude().getId());
        System.out.println("\tAdmin - " + entity.getAdmin().getEmail() + "; ID - " + entity.getAdmin().getId());
        System.out.println("####################################################");

        return oRepository.save(entity);
    }

    @Override
    public KulturnaPonuda update(KulturnaPonuda entity, Integer id) throws Exception {
        KulturnaPonuda existingKulturnaPonuda = oRepository.findById(id).orElse(null);
        if(existingKulturnaPonuda == null){
            throw new Exception("KulturnaPonuda with given id doesn't exist");
        }
        System.out.println("B");
        existingKulturnaPonuda.setNaziv(entity.getNaziv());
        System.out.println("B");
        existingKulturnaPonuda.setAdresa(entity.getAdresa());
        System.out.println("B");
        existingKulturnaPonuda.setOpis(entity.getOpis());
        System.out.println("B");
        //existingKulturnaPonuda.setRegistrovaniKorisnik(entity.getRegistrovaniKorisnik());
        //existingKulturnaPonuda.setFotogrfija(entity.getFotogrfija());
        System.out.println("B");
        TipKulturnePonude tip = oTip.getOne(Integer.parseInt(entity.getTipKulturnePonude().getNaziv()));
        System.out.println("B");
        entity.setTipKulturnePonude(tip);
        if( entity.getTipKulturnePonude() != null)
            existingKulturnaPonuda.setTipKulturnePonude(entity.getTipKulturnePonude());
        System.out.println("B");
        Lokacija lokacija = lokacijaRepository.getOne(Integer.parseInt(entity.getLokacija().getNazivLokacije()));
        entity.setLokacija(lokacija);
        System.out.println("B");
        if( entity.getLokacija() != null)
            existingKulturnaPonuda.setLokacija(entity.getLokacija());
        System.out.println("B");
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

    public Page<KulturnaPonuda> findAll(Pageable pageable) {
        return oRepository.findAll(pageable);
    }

    public KulturnaPonuda save(KulturnaPonuda k){
        return oRepository.save(k);
    }

    public List<KulturnaPonuda> filterByContent(String content) {
        return oRepository.findDistinctByNazivContainingOrOpisContainingOrderByNaziv(content, content);
    }

    public List<KulturnaPonuda> filterByLocation(String name) {
        Lokacija lokacija = lokacijaRepository.findByNazivLokacije(name);
        return oRepository.findByLokacija(lokacija);
    }

    public Page<KulturnaPonuda> filterByLocationPage(Pageable pageable, String name) {
        Lokacija lokacija = lokacijaRepository.findByNazivLokacije(name);
        return  oRepository.findByLokacija(pageable, lokacija);
    }

    public Page<KulturnaPonuda> filterByContentPage(Pageable pageable, String content) {
        return oRepository.findDistinctByNazivContainingOrOpisContainingOrderByNaziv(pageable, content, content);
    }
}
