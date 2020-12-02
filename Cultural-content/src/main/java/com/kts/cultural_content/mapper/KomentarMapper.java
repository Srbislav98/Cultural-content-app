package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.KomentarDTO;
import com.kts.cultural_content.model.Komentar;

public class KomentarMapper implements MapperInterface<Komentar, KomentarDTO>{

    @Override
    public Komentar toEntity(KomentarDTO dto){return new Komentar(dto.getVrednost(), dto.getRegistrovaniKorisnik(), dto.getKulturnaPonuda(), dto.getFotogrfija());}

    @Override
    public KomentarDTO toDto(Komentar entity) {return new KomentarDTO(entity.getId(), entity.getVrednost(), entity.getRegistrovaniKorisnik(), entity.getKulturnaPonuda(), entity.getFotogrfija());}

}
