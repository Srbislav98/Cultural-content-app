package com.kts.cultural_content.model;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name= "KORISNICI")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Korisnik {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String ime;

    @Column(nullable=false)
    private String prezime;

    @Column(nullable=false,unique = true)
    private String korisnickoIme;

    @Column(nullable=false,unique = true)
    private String email;

    @Column(nullable=false)
    private String lozinka;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( nullable = false)
    private Uloga uloga;

    public Korisnik() {
    }

    public Korisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, Uloga uloga) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.lozinka = lozinka;
        this.uloga = uloga;
    }

    public Korisnik(RegistrovaniKorisnikDTO rk) {
        this.id = rk.getId();
        this.ime = rk.getIme();
        this.prezime = rk.getPrezime();
        this.korisnickoIme = rk.getKorisnickoIme();
        this.email = rk.getEmail();
        this.lozinka = rk.getLozinka();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }
}
