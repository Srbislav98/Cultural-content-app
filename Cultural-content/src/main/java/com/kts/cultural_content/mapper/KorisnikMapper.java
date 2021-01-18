package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.KorisnikDTO;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.model.*;

import java.util.HashSet;
import java.util.Set;

public class KorisnikMapper implements MapperInterface<Korisnik, KorisnikDTO> {

    public KorisnikMapper() {
    }
    @Override
    public Korisnik toEntity(KorisnikDTO dto) {
        return new Korisnik(dto.getId(),dto.getIme(), dto.getPrezime(),dto.getKorisnickoIme(),
                dto.getEmail(), dto.getLozinka());
    }

    @Override
    public KorisnikDTO toDto(Korisnik entity) {
        return new KorisnikDTO(entity.getId(),entity.getIme(), entity.getPrezime(),entity.getKorisnickoIme(),
                entity.getEmail(), entity.getLozinka());
    }

}

