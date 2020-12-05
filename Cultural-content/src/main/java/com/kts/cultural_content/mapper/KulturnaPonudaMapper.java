package com.kts.cultural_content.mapper;
import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.mapper.MapperInterface;
import com.kts.cultural_content.model.*;
import com.kts.cultural_content.repository.TipKPRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class KulturnaPonudaMapper implements MapperInterface<KulturnaPonuda, KulturnaPonudaDTO> {
    @Autowired
    private TipKPRepository oTip;

    @Override
    public KulturnaPonuda toEntity(KulturnaPonudaDTO dto) {
        return new KulturnaPonuda(dto.getId(), dto.getNaziv(), dto.getGeoSirina(), dto.getGeoDuzina(), dto.getAdresa(), dto.getOpis());
    }

    @Override
    public KulturnaPonudaDTO toDto(KulturnaPonuda entity) {
        return new KulturnaPonudaDTO(entity.getId(), entity.getNaziv(), entity.getGeoSirina(), entity.getGeoDuzina(), entity.getAdresa(), entity.getOpis());
        }
}
