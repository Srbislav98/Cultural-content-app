package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.model.Ocena;

public class OcenaMapper implements MapperInterface<Ocena, OcenaDTO> {

    @Override
    public Ocena toEntity(OcenaDTO dto){return new Ocena(dto.getVrednost(), dto.getRegistrovaniKorisnik(), dto.getKulturnaPonuda());}

    @Override
    public OcenaDTO toDto(Ocena entity) {return new OcenaDTO(entity.getId(), entity.getVrednost(), entity.getRegistrovaniKorisnik(), entity.getKulturnaPonuda());}
}
