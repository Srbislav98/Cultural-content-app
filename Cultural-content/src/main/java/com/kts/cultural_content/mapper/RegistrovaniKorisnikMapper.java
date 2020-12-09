package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.KomentarDTO;
import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.model.*;

import java.util.HashSet;
import java.util.Set;

public class RegistrovaniKorisnikMapper implements MapperInterface<RegistrovaniKorisnik, RegistrovaniKorisnikDTO> {

    private UlogaMapper ulogaMapper;
    private KulturnaPonudaMapper kulturnaPonudaMapper;
    private KomentarMapper komentarMapper;
    private OcenaMapper ocenaMapper;
    public RegistrovaniKorisnikMapper() {
        ulogaMapper = new UlogaMapper();
        kulturnaPonudaMapper=new KulturnaPonudaMapper();
        komentarMapper=new KomentarMapper();
        ocenaMapper=new OcenaMapper();
    }
    @Override
    public RegistrovaniKorisnik toEntity(RegistrovaniKorisnikDTO dto) {
        Set<KulturnaPonuda> kulturnaPonuda = new HashSet<>();
        if(!dto.getKulturnaPonuda().isEmpty()){
            for (KulturnaPonudaDTO kp:dto.getKulturnaPonuda()){
                kulturnaPonuda.add(kulturnaPonudaMapper.toEntity(kp));
            }
        }
        Set<Komentar> komentari = new HashSet<>();
        if(!dto.getKulturnaPonuda().isEmpty()) {
            for (KomentarDTO kp : dto.getKomentari()) {
                komentari.add(komentarMapper.toEntity(kp));
            }
        }
        Set<Ocena> ocene = new HashSet<>();
        if(!dto.getKulturnaPonuda().isEmpty()) {
            for (OcenaDTO kp : dto.getOcene()) {
                ocene.add(ocenaMapper.toEntity(kp));
            }
        }
        return new RegistrovaniKorisnik(dto.getId(),dto.getIme(), dto.getPrezime(),dto.getKorisnickoIme(),
                dto.getEmail(), dto.getLozinka(),kulturnaPonuda,komentari,ocene);
    }

    @Override
    public RegistrovaniKorisnikDTO toDto(RegistrovaniKorisnik entity) {
        Set<KulturnaPonudaDTO> kulturnaPonuda = new HashSet<>();
        for (KulturnaPonuda kp:entity.getKulturnaPonuda()){
            kulturnaPonuda.add(kulturnaPonudaMapper.toDto(kp));
        }
        Set<KomentarDTO> komentari = new HashSet<>();
        for (Komentar kp:entity.getKomentari()){
            komentari.add(komentarMapper.toDto(kp));
        }
        Set<OcenaDTO> ocene = new HashSet<>();
        for (Ocena kp:entity.getOcene()){
            ocene.add(ocenaMapper.toDto(kp));
        }
        return new RegistrovaniKorisnikDTO(entity.getId(),entity.getIme(), entity.getPrezime(),entity.getKorisnickoIme(),
                entity.getEmail(), entity.getLozinka(),kulturnaPonuda,komentari,ocene);
    }

}
