package com.kts.cultural_content.model;

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

    @ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "user_authority",
    //        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    //        inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Uloga> uloge;

    public Korisnik() {
    }

    public Korisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, List<Uloga> uloge) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.lozinka = lozinka;
        this.uloge = uloge;
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

    public List<Uloga> getUloge() {
        return uloge;
    }

    public void setUloge(List<Uloga> uloge) {
        this.uloge = uloge;
    }
}
