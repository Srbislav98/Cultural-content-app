package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.Uloga;

import javax.persistence.*;

public class KorisnikDTO implements Comparable<KorisnikDTO> {

    private Integer id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String email;
    private String lozinka;


    public KorisnikDTO(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.lozinka = lozinka;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
/*
    public UlogaDTO getUloga() {
        return uloga;
    }

    public void setUloga(UlogaDTO uloga) {
        this.uloga = uloga;
    }
*/
    @Override
    public int compareTo(KorisnikDTO o) {
        return this.email.compareTo(o.email);
    }
}
