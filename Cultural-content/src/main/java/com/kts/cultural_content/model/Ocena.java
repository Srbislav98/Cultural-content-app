package com.kts.cultural_content.model;

import javax.persistence.*;

@Entity

public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = true)
    private Integer vrednost;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //@JoinColumn( nullable = false)
    private RegistrovaniKorisnik registrovaniKorisnik;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //@JoinColumn( nullable = false)
    private KulturnaPonuda kulturnaPonuda;

    public Ocena() {
    }

    public Ocena(Integer vrednost, RegistrovaniKorisnik registrovaniKorisnik, KulturnaPonuda kulturnaPonuda) {

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
