package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.RecenzijaDTO;
import com.kts.cultural_content.model.Recenzija;

public class RecenzijaMapper implements MapperInterface<Recenzija, RecenzijaDTO> {
    @Override
    public Recenzija toEntity(RecenzijaDTO dto){return new Recenzija(dto.getId(), dto.getOcena(),dto.getKomentar(),dto.getRegId(),dto.getKulId(),dto.getFotoLokacija());}

    @Override
    public RecenzijaDTO toDto(Recenzija entity){return new RecenzijaDTO(entity.getId(),entity.getOcena(),entity.getKomentar(),entity.getRegId(),entity.getKulId(),entity.getFotoLokacija());}
}
