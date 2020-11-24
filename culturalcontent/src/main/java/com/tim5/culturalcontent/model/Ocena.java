package com.tim5.culturalcontent.model;

import javax.persistence.*;

@Entity
@Table(name="Ocene")
public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vrednost", nullable = true)
    private Integer vrednost;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "Registrovan korisnik", nullable = false)
    private RegistrovaniKorisnik registrovaniKorisnik;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "Kulturna ponuda", nullable = false)
    private KulturnaPonuda kulturnaPonuda;

    public Ocena() {
    }

    public Ocena(Integer id, Integer vrednost, RegistrovaniKorisnik registrovaniKorisnik, KulturnaPonuda kulturnaPonuda) {
        this.id = id;
        this.vrednost = vrednost;
        this.registrovaniKorisnik = registrovaniKorisnik;
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVrednost(Integer vrednost) {
        this.vrednost = vrednost;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVrednost() {
        return vrednost;
    }

    public void setRegistrovaniKorisnik(RegistrovaniKorisnik registrovaniKorisnik) {
        this.registrovaniKorisnik = registrovaniKorisnik;
    }

    public void setKulturnaPonuda(KulturnaPonuda kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public RegistrovaniKorisnik getRegistrovaniKorisnik() {
        return registrovaniKorisnik;
    }

    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }
}
