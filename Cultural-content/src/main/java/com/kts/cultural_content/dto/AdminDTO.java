package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;

import java.util.HashSet;
import java.util.Set;

public class AdminDTO extends KorisnikDTO {
    private Set<KulturnaPonudaDTO> kulturnePonude = new HashSet<>();

    public AdminDTO(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, Set<KulturnaPonudaDTO> kulturnePonude) {
        super(id, ime, prezime, korisnickoIme, email, lozinka);
        this.kulturnePonude = kulturnePonude;
    }

    public Set<KulturnaPonudaDTO> getKulturnePonude() {
        return kulturnePonude;
    }

    public void setKulturnePonude(Set<KulturnaPonudaDTO> kulturnePonude) {
        this.kulturnePonude = kulturnePonude;
    }
}
