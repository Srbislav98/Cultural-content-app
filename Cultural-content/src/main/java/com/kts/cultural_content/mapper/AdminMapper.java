package com.kts.cultural_content.mapper;

import com.kts.cultural_content.dto.AdminDTO;
import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;

import java.util.HashSet;
import java.util.Set;

public class AdminMapper implements MapperInterface<Admin,AdminDTO>{

    private KulturnaPonudaMapper kulturnaPonudaMapper;
    private UlogaMapper ulogaMapper;

    @Override
    public Admin toEntity(AdminDTO dto) {
        Set<KulturnaPonuda> kulturnaPonuda = new HashSet<>();
        for (KulturnaPonudaDTO kp:dto.getKulturnePonude()){
            kulturnaPonuda.add(kulturnaPonudaMapper.toEntity(kp));
        }
        return new Admin(dto.getId(),dto.getIme(), dto.getPrezime(),dto.getKorisnickoIme(),
                dto.getEmail(), dto.getLozinka(), ulogaMapper.toEntity(dto.getUloga()),kulturnaPonuda);
    }

    @Override
    public AdminDTO toDto(Admin entity) {
        Set<KulturnaPonudaDTO> kulturnaPonuda = new HashSet<>();
        for (KulturnaPonuda kp:entity.getKulturnePonude()){
            kulturnaPonuda.add(kulturnaPonudaMapper.toDto(kp));
        }
        return new AdminDTO(entity.getId(),entity.getIme(), entity.getPrezime(),entity.getKorisnickoIme(),
                entity.getEmail(), entity.getLozinka(), ulogaMapper.toDto(entity.getUloga()),kulturnaPonuda);
    }
}
