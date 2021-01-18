package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.dto.RecenzijaDTO;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.model.*;

import java.util.HashSet;
import java.util.Set;

public class RegistrovaniKorisnikMapper implements MapperInterface<RegistrovaniKorisnik, RegistrovaniKorisnikDTO> {

    private UlogaMapper ulogaMapper;
    private KulturnaPonudaMapper kulturnaPonudaMapper;
    private RecenzijaMapper recenzijaMapper;

    public RegistrovaniKorisnikMapper() {
        ulogaMapper = new UlogaMapper();
        kulturnaPonudaMapper=new KulturnaPonudaMapper();
        recenzijaMapper=new RecenzijaMapper();
    }
    @Override
    public RegistrovaniKorisnik toEntity(RegistrovaniKorisnikDTO dto) {
        Set<KulturnaPonuda> kulturnaPonuda = new HashSet<>();
        if(!dto.getKulturnaPonuda().isEmpty()){
            for (KulturnaPonudaDTO kp:dto.getKulturnaPonuda()){
                kulturnaPonuda.add(kulturnaPonudaMapper.toEntity(kp));
            }
        }
        Set<Recenzija> recenzije = new HashSet<>();
        if(!dto.getKulturnaPonuda().isEmpty()) {
            for (RecenzijaDTO kp : dto.getRecenzije()) {
                recenzije.add(recenzijaMapper.toEntity(kp));
            }
        }

        return new RegistrovaniKorisnik(dto.getId(),dto.getIme(), dto.getPrezime(),dto.getKorisnickoIme(),
                dto.getEmail(), dto.getLozinka(),kulturnaPonuda,recenzije);
    }

    @Override
    public RegistrovaniKorisnikDTO toDto(RegistrovaniKorisnik entity) {
        Set<KulturnaPonudaDTO> kulturnaPonuda = new HashSet<>();
        for (KulturnaPonuda kp:entity.getKulturnaPonuda()){
            kulturnaPonuda.add(kulturnaPonudaMapper.toDto(kp));
        }
        Set<RecenzijaDTO> recenzije = new HashSet<>();
        for (Recenzija kp:entity.getRecenzije()){
            recenzije.add(recenzijaMapper.toDto(kp));
        }

        return new RegistrovaniKorisnikDTO(entity.getId(),entity.getIme(), entity.getPrezime(),entity.getKorisnickoIme(),
                entity.getEmail(), entity.getLozinka(),kulturnaPonuda,recenzije);
    }

}
