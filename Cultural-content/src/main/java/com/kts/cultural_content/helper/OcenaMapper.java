package com.kts.cultural_content.helper;

import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.helper.MapperInterface;

public class OcenaMapper implements MapperInterface<Ocena, OcenaDTO> {

    @Override
    public Ocena toEntity(OcenaDTO dto){return new Ocena(dto.getVrednost(), dto.getRegistrovaniKorisnik(), dto.getKulturnaPonuda());}

    @Override
    public OcenaDTO toDto(Ocena entity) {return new OcenaDTO(entity.getId(), entity.getVrednost(), entity.getRegistrovaniKorisnik(), entity.getKulturnaPonuda());}
}
