package com.kts.cultural_content.mapper;
import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.mapper.MapperInterface;
import com.kts.cultural_content.model.*;
import com.kts.cultural_content.repository.TipKPRepository;
import com.kts.cultural_content.service.LokacijaService;
import com.kts.cultural_content.service.TipKPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Optional;

public class KulturnaPonudaMapper implements MapperInterface<KulturnaPonuda, KulturnaPonudaDTO> {

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

        Lokacija lokacija = new Lokacija(dto.getIdLokacije(), dto.getIdLokacije().toString());

        return new KulturnaPonuda(dto.getId(), dto.getNaziv(), dto.getAdresa(), dto.getOpis(), tip, ad, lokacija);
    }

    @Override
    public KulturnaPonudaDTO toDto(KulturnaPonuda entity) {
        return new KulturnaPonudaDTO(entity.getId(), entity.getNaziv(), entity.getAdresa(), entity.getOpis(), entity.getIdt(), entity.getLokacija().getId());
    }
}
