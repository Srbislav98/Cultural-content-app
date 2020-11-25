package com.kts.cultural_content.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RegistrovaniKorisnik extends Korisnik {
    public RegistrovaniKorisnik() {
    }

    public RegistrovaniKorisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, Uloga uloga) {
        super(id, ime, prezime, korisnickoIme, email, lozinka, uloga);
    }

    @ManyToMany(mappedBy = "registrovaniKorisnik", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<KulturnaPonuda> kulturnaPonuda = new HashSet<>();

    public void setKulturnaPonuda(Set<KulturnaPonuda> kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public Set<KulturnaPonuda> getKulturnaPonuda() {
        return kulturnaPonuda;
    }
}
