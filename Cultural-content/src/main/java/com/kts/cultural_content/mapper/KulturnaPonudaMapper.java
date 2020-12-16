package com.kts.cultural_content.mapper;
import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.mapper.MapperInterface;
import com.kts.cultural_content.model.*;
import com.kts.cultural_content.repository.TipKPRepository;
import com.kts.cultural_content.service.TipKPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Optional;

public class KulturnaPonudaMapper implements MapperInterface<KulturnaPonuda, KulturnaPonudaDTO> {
    @Autowired
    private TipKPService oTip;

    @Override
    public KulturnaPonuda toEntity(KulturnaPonudaDTO dto) {
        System.out.println("#toEntity###########################################");
        Object admin = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Admin ad = (Admin) admin;

        System.out.println("Admin : ");
        System.out.println("\tID - " + ad.getId());
        System.out.println("\tNaziv - " + ad.getIme());
        System.out.println("\tEmail - " + ad.getEmail());
        System.out.println("-----------------------------------------------------");

        TipKulturnePonude tip = new TipKulturnePonude(1, dto.getIdt().toString());

        System.out.println("Tip KP : ");
        System.out.println("\tID - " + tip.getId());
        System.out.println("\tNaziv - " + tip.getNaziv());
        System.out.println("-----------------------------------------------------");


        return new KulturnaPonuda(dto.getId(), dto.getNaziv(), dto.getGeoSirina(), dto.getGeoDuzina(), dto.getAdresa(), dto.getOpis(), tip, ad);
    }

    @Override
    public KulturnaPonudaDTO toDto(KulturnaPonuda entity) {
        return new KulturnaPonudaDTO(entity.getId(), entity.getNaziv(), entity.getGeoSirina(), entity.getGeoDuzina(), entity.getAdresa(), entity.getOpis(), entity.getIdt());
    }
}
