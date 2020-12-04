package com.kts.cultural_content.model;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name= "KORISNICI")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
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

    @ManyToMany()
    //@LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Uloga> uloga;

    public Korisnik() {
    }

    public Korisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.lozinka = lozinka;
        //this.uloga = uloga;
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

    public List<Uloga> getUloga() {
        return uloga;
    }

    public void setUloga(List<Uloga> uloga) {
        this.uloga = uloga;
    }
}
