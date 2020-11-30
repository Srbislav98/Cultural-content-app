package com.kts.cultural_content.model;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RegistrovaniKorisnik extends Korisnik {

    @ManyToMany(mappedBy = "registrovaniKorisnik", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<KulturnaPonuda> kulturnaPonuda = new HashSet<>();

    @OneToMany( mappedBy = "registrovaniKorisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    @OneToMany( mappedBy = "registrovaniKorisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> ocene = new HashSet<>();

    public RegistrovaniKorisnik() {
    }

    public RegistrovaniKorisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, Uloga uloga,
                                Set<KulturnaPonuda> kulturnaPonude, Set<Komentar> komentari, Set<Ocena> ocene) {
        super(id, ime, prezime, korisnickoIme, email, lozinka, uloga);
        this.kulturnaPonuda=kulturnaPonude;
        this.komentari=komentari;
        this.ocene=ocene;
    }

    public RegistrovaniKorisnik(RegistrovaniKorisnikDTO rk) {
        super(rk);
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    public Set<Ocena> getOcene() {
        return ocene;
    }

    public void setOcene(Set<Ocena> ocene) {
        this.ocene = ocene;
    }

    public void setKulturnaPonuda(Set<KulturnaPonuda> kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public Set<KulturnaPonuda> getKulturnaPonuda() {
        return kulturnaPonuda;
    }
}
