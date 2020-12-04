package com.kts.cultural_content.mapper;
import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.mapper.MapperInterface;
import com.kts.cultural_content.model.*;

import java.util.ArrayList;

public class KulturnaPonudaMapper implements MapperInterface<KulturnaPonuda, KulturnaPonudaDTO> {
    @Override
    public KulturnaPonuda toEntity(KulturnaPonudaDTO dto) {
        return new KulturnaPonuda(dto.getId(), dto.getNaziv(), dto.getGeoSirina(), dto.getGeoDuzina(), dto.getAdresa(), dto.getOpis(),dto.getFotogrfija(), dto.getAdmin(), dto.getTipKulturnePonude(),dto.getOcene(),dto.getKomentari(),dto.getNovosti(),(ArrayList<RegistrovaniKorisnik>)dto.getRegistrovaniKorisnik());
    }

    @Override
    public KulturnaPonudaDTO toDto(KulturnaPonuda entity) {
        return new KulturnaPonudaDTO(entity.getId(), entity.getNaziv(), entity.getGeoSirina(), entity.getGeoDuzina(), entity.getAdresa(), entity.getOpis(),entity.getFotogrfija(), entity.getAdmin(), entity.getTipKulturnePonude(), (ArrayList<RegistrovaniKorisnik>) entity.getRegistrovaniKorisnik(), entity.getOcene(),entity.getKomentari(),entity.getNovosti());
        }
}
