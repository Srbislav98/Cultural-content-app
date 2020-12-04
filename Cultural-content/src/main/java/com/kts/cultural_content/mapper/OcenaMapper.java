package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.mapper.MapperInterface;
import com.kts.cultural_content.model.Ocena;


public class OcenaMapper implements MapperInterface<Ocena, OcenaDTO> {

    @Override
    public Ocena toEntity(OcenaDTO dto){return new Ocena(dto.getVrednost());}

    @Override
    public OcenaDTO toDto(Ocena entity) {return new OcenaDTO(entity.getId(), entity.getVrednost());}
}
