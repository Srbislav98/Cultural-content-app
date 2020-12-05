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
    public AdminMapper() {
        ulogaMapper = new UlogaMapper();
        kulturnaPonudaMapper=new KulturnaPonudaMapper();
    }

    @Override
    public Admin toEntity(AdminDTO dto) {
        Set<KulturnaPonuda> kulturnaPonuda = new HashSet<>();
        if(!dto.getKulturnePonude().isEmpty()){
            for (KulturnaPonudaDTO kp:dto.getKulturnePonude()){
                kulturnaPonuda.add(kulturnaPonudaMapper.toEntity(kp));
            }
        }
        return new Admin(dto.getId(),dto.getIme(), dto.getPrezime(),dto.getKorisnickoIme(),
                dto.getEmail(), dto.getLozinka(),kulturnaPonuda);
    }

    @Override
    public AdminDTO toDto(Admin entity) {
        Set<KulturnaPonudaDTO> kulturnaPonuda = new HashSet<>();
        if(!entity.getKulturnePonude().isEmpty()) {
            for (KulturnaPonuda kp : entity.getKulturnePonude()) {
                kulturnaPonuda.add(kulturnaPonudaMapper.toDto(kp));
            }
        }
        return new AdminDTO(entity.getId(),entity.getIme(), entity.getPrezime(),entity.getKorisnickoIme(),
                entity.getEmail(), entity.getLozinka(),kulturnaPonuda);
    }
}
