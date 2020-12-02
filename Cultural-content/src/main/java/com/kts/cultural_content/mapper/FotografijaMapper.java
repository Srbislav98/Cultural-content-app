package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.FotografijaDTO;

import com.kts.cultural_content.model.Fotografija;


public class FotografijaMapper implements MapperInterface<Fotografija, FotografijaDTO> {

    @Override
    public Fotografija toEntity(FotografijaDTO dto){return new Fotografija(dto.getNaziv(), dto.getLokacijaFajl(), dto.getKulturnaPonuda(), dto.getKomentar());}

    @Override
    public FotografijaDTO toDto(Fotografija entity) {return new FotografijaDTO(entity.getId(), entity.getNaziv(), entity.getLokacijaFajl(), entity.getKulturnaPonuda(), entity.getKomentar());}

}
