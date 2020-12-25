package com.kts.cultural_content.model;

import javax.persistence.*;

@Entity

public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = true)
    private Integer vrednost;

    @Column
    private Integer regId;

    @Column
    private Integer kulId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private RegistrovaniKorisnik registrovaniKorisnik;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private KulturnaPonuda kulturnaPonuda;

    public Ocena() {
    }

    public Ocena(Integer vrednost, Integer regId, Integer kulId) {
        this.vrednost = vrednost;
        this.regId = regId;
        this.kulId = kulId;
    }

    @Override
    public boolean equals(Object obj) {
        Ocena o = (Ocena)obj;
        if(this.getId()==null || o.getId()==null){
            return this.getRegId().equals(o.getRegId()) && this.getKulId().equals(o.getKulId());
        }else
            return this.getId().equals(o.getId());
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public void setKulId(Integer kulId) {
        this.kulId = kulId;
    }

    public Integer getRegId() {
        return regId;
    }

    public Integer getKulId() {
        return kulId;
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
