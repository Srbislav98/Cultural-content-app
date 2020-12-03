package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.TipKulturnePonudeDTO;
import com.kts.cultural_content.model.TipKulturnePonude;

public class TipKulturnePonudeMapper implements MapperInterface<TipKulturnePonude, TipKulturnePonudeDTO> {
    @Override
    public TipKulturnePonude toEntity(TipKulturnePonudeDTO dto) {
        return new TipKulturnePonude(dto.getId(), dto.getNaziv());
    }

    @Override
    public TipKulturnePonudeDTO toDto(TipKulturnePonude entity) {
        return new TipKulturnePonudeDTO(entity.getId(), entity.getNaziv());
    }
}
