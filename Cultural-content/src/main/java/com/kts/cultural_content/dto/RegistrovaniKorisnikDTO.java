package com.kts.cultural_content.dto;


import com.kts.cultural_content.model.RegistrovaniKorisnik;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.*;

public class RegistrovaniKorisnikDTO extends KorisnikDTO{

    private Set<KulturnaPonudaDTO> kulturnaPonuda = new HashSet<>();
    private Set<RecenzijaDTO> recenzije = new HashSet<>();

    public RegistrovaniKorisnikDTO(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka,  Set<KulturnaPonudaDTO> kulturnaPonuda, Set<RecenzijaDTO> recenzije) {
        super(id, ime, prezime, korisnickoIme, email, lozinka);
        this.kulturnaPonuda = kulturnaPonuda;
        this.recenzije = recenzije;

    }

    public RegistrovaniKorisnikDTO() {

    }

    public RegistrovaniKorisnikDTO(KorisnikDTO a) {
        this.setId(a.getId());
        this.setIme(a.getIme());
        this.setPrezime(a.getPrezime());
        this.setKorisnickoIme(a.getKorisnickoIme());
        this.setEmail(a.getEmail());
        this.setLozinka(a.getLozinka());
    }

    public RegistrovaniKorisnikDTO(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka) {
        super(id, ime, prezime, korisnickoIme, email, lozinka);
    }

    public Set<KulturnaPonudaDTO> getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    public void setKulturnaPonuda(Set<KulturnaPonudaDTO> kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public Set<RecenzijaDTO> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Set<RecenzijaDTO> recenzije) {
        this.recenzije = recenzije;
    }
}
