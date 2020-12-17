package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.TipKulturnePonude;
import com.kts.cultural_content.repository.AdminRepository;
import com.kts.cultural_content.repository.KulturnaPonudaRepository;
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

        existingKulturnaPonuda.setNaziv(entity.getNaziv());
        existingKulturnaPonuda.setAdresa(entity.getAdresa());
        existingKulturnaPonuda.setGeoDuzina(entity.getGeoDuzina());
        existingKulturnaPonuda.setGeoSirina(entity.getGeoSirina());
        existingKulturnaPonuda.setOpis(entity.getOpis());

        //existingKulturnaPonuda.setRegistrovaniKorisnik(entity.getRegistrovaniKorisnik());
        //existingKulturnaPonuda.setFotogrfija(entity.getFotogrfija());

        TipKulturnePonude tip = oTip.getOne(Integer.parseInt(entity.getTipKulturnePonude().getNaziv()));
        entity.setTipKulturnePonude(tip);
        if( entity.getTipKulturnePonude() != null)
            existingKulturnaPonuda.setTipKulturnePonude(entity.getTipKulturnePonude());

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
        List<KulturnaPonuda> poNazivu = oRepository.findByNazivContainingOrderByNaziv(content);
        List<KulturnaPonuda> poOpisu = oRepository.findByOpisContainingOrderById(content);
        List<KulturnaPonuda> poSadrzaju = new ArrayList<>();
        poSadrzaju.addAll(poNazivu);
        poSadrzaju.addAll(poOpisu);
        return poSadrzaju;
    }

    public List<KulturnaPonuda> filterByLocation(String x, String y) {
        return oRepository.findByGeoSirinaAndGeoDuzinaOrderByNaziv(x, y);
    }
}
