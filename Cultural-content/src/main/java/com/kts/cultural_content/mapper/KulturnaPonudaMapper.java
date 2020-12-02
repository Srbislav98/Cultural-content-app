package com.kts.cultural_content.mapper;
import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.helper.MapperInterface;
import com.kts.cultural_content.model.KulturnaPonuda;

public class KulturnaPonudaMapper implements MapperInterface<KulturnaPonuda, KulturnaPonudaDTO> {
    @Override
    public KulturnaPonuda toEntity(KulturnaPonudaDTO dto) {
        return new KulturnaPonuda(dto.getId(), dto.getNaziv(), dto.getGeoSirina(), dto.getGeoDuzina(), dto.getAdresa(), dto.getOpis(), dto.getAdmin(), dto.getTipKulturnePonude());
    }

    @Override
    public KulturnaPonudaDTO toDto(KulturnaPonuda entity) {
        return new KulturnaPonudaDTO(entity.getId(), entity.getNaziv(), entity.getGeoSirina(), entity.getGeoDuzina(), entity.getAdresa(), entity.getOpis(), entity.getAdmin());
    }
}
