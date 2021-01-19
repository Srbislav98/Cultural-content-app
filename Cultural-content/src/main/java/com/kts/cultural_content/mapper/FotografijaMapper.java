package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.FotografijaDTO;

import com.kts.cultural_content.model.Fotografija;


public class FotografijaMapper implements MapperInterface<Fotografija, FotografijaDTO> {

    @Override
    public Fotografija toEntity(FotografijaDTO dto){return new Fotografija( dto.getLokacijaFajl(),dto.getKulId(),dto.getRecId());}

    @Override
    public FotografijaDTO toDto(Fotografija entity) {return new FotografijaDTO(entity.getId(), entity.getLokacijaFajl(),entity.getKulId(),entity.getRecId());}

}
