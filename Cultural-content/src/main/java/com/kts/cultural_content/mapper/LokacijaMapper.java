package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.LokacijaDTO;
import com.kts.cultural_content.model.Lokacija;

public class LokacijaMapper implements MapperInterface<Lokacija, LokacijaDTO>{
    @Override
    public Lokacija toEntity(LokacijaDTO dto) {
        return new Lokacija(dto.getId(), dto.getNazivLokacije(), dto.getGeoDuzina(), dto.getGeoSirina());
    }

    @Override
    public LokacijaDTO toDto(Lokacija entity) {
        return new LokacijaDTO(entity.getId(), entity.getNazivLokacije(), entity.getGeoDuzina(), entity.getGeoSirina());
    }
}
