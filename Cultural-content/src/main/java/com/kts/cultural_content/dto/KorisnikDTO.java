package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.Uloga;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class KorisnikDTO implements Comparable<KorisnikDTO> {

    private Integer id;

    @NotBlank(message = "Ime cannot be empty.")
    private String ime;

    @NotBlank(message = "Prezime cannot be empty.")
    private String prezime;

    @NotBlank(message = "Korisnicko ime cannot be empty.")
    private String korisnickoIme;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Email format is not valid.")
    private String email;

    @NotBlank(message = "Lozinka cannot be empty.")
    private String lozinka;

    public KorisnikDTO(){

    }
    public KorisnikDTO(Integer id, @NotBlank(message = "Ime cannot be empty.") String ime, @NotBlank(message = "Prezime cannot be empty.")String prezime,
                       @NotBlank(message = "Korisnicko ime cannot be empty.") String korisnickoIme,@NotBlank(message = "Email cannot be empty.")
                       @Email(message = "Email format is not valid.") String email,@NotBlank(message = "Lozinka cannot be empty.") String lozinka) {
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
