package com.kts.cultural_content.model;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class RegistrovaniKorisnik extends Korisnik {



    @ManyToMany(  cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = {
                    @JoinColumn(
                            nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(
                            nullable = false)})
    private Set<KulturnaPonuda> kulturnaPonuda;

    @OneToMany( mappedBy = "registrovaniKorisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    @OneToMany( mappedBy = "registrovaniKorisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> ocene = new HashSet<>();

    public RegistrovaniKorisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka) {
        super(id, ime, prezime, korisnickoIme, email, lozinka);
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Column(nullable = true)
    private String resetPasswordToken;

    public RegistrovaniKorisnik() {
    }

    public RegistrovaniKorisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka,
                                Set<KulturnaPonuda> kulturnaPonude, Set<Komentar> komentari, Set<Ocena> ocene) {
        super(id, ime, prezime, korisnickoIme, email, lozinka);
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
