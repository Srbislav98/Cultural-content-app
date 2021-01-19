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
    private Set<Recenzija> recenzije = new HashSet<>();

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
                                Set<KulturnaPonuda> kulturnaPonude, Set<Recenzija> recenzije) {
        super(id, ime, prezime, korisnickoIme, email, lozinka);
        this.kulturnaPonuda=kulturnaPonude;
        this.recenzije=recenzije;

    }

    public RegistrovaniKorisnik(RegistrovaniKorisnikDTO rk) {
        super(rk);
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Set<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public void setKulturnaPonuda(Set<KulturnaPonuda> kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public Set<KulturnaPonuda> getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    @Override
    public boolean equals(Object obj) {
        RegistrovaniKorisnik o = (RegistrovaniKorisnik) obj;
        return this.getId().equals(o.getId()) && this.getEmail().equals((o.getEmail()))
                && this.getUsername().equals((o.getUsername()));
    }
}
