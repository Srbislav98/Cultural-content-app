package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.NovostDTO;
import com.kts.cultural_content.model.Novost;

public class NovostMapper implements MapperInterface<Novost, NovostDTO> {

    @Override
    public Novost toEntity(NovostDTO dto) {
        return new Novost(dto.getId(), dto.getNaziv(), dto.getOpis(), dto.getDatum(), dto.getKulturnaPonuda());
    }

    @Override
    public NovostDTO toDto(Novost entity) {
        return new NovostDTO(entity.getId(), entity.getNaziv(), entity.getOpis(), entity.getDatum(), entity.getKulturnaPonuda());
    }
}
