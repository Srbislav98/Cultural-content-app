package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.UlogaDTO;
import com.kts.cultural_content.model.Uloga;

public class UlogaMapper implements MapperInterface<Uloga, UlogaDTO> {

    public UlogaMapper() {
    }
    @Override
    public Uloga toEntity(UlogaDTO dto) {
        return new Uloga(dto.getId(),dto.getIme());
    }

    @Override
    public UlogaDTO toDto(Uloga entity) {
        return new UlogaDTO(entity.getId(),entity.getIme());
    }
}
