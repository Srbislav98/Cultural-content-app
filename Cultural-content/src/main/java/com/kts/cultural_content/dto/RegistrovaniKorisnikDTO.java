package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.model.RegistrovaniKorisnik;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RegistrovaniKorisnikDTO extends KorisnikDTO{

    private Set<KulturnaPonudaDTO> kulturnaPonuda = new HashSet<>();
    private Set<KomentarDTO> komentari = new HashSet<>();
    private Set<OcenaDTO> ocene = new HashSet<>();

    public RegistrovaniKorisnikDTO(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka,  Set<KulturnaPonudaDTO> kulturnaPonuda, Set<KomentarDTO> komentari, Set<OcenaDTO> ocene) {
        super(id, ime, prezime, korisnickoIme, email, lozinka);
        this.kulturnaPonuda = kulturnaPonuda;
        this.komentari = komentari;
        this.ocene = ocene;
    }

    public Set<KulturnaPonudaDTO> getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    public void setKulturnaPonuda(Set<KulturnaPonudaDTO> kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public Set<KomentarDTO> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<KomentarDTO> komentari) {
        this.komentari = komentari;
    }

    public Set<OcenaDTO> getOcene() {
        return ocene;
    }

    public void setOcene(Set<OcenaDTO> ocene) {
        this.ocene = ocene;
    }
}
