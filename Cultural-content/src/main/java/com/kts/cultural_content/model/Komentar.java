package com.kts.cultural_content.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String vrednost;

    @Column
    private Integer regId;

    @Column
    private Integer kulId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //@JoinColumn( nullable = false)
    private RegistrovaniKorisnik registrovaniKorisnik;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    //@JoinColumn( nullable = false)
    private KulturnaPonuda kulturnaPonuda;

    @OneToOne(mappedBy = "komentar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Fotografija fotogrfija ;

    public Komentar(String vrednost, Integer regId, Integer kulId) {
        this.vrednost = vrednost;
        this.regId = regId;
        this.kulId = kulId;

    }

    @Override
    public boolean equals(Object obj) {
        Komentar o = (Komentar)obj;
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

    public Komentar() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    public Integer getId() {
        return id;
    }

    public String getVrednost() {
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

    public void setFotogrfija(Fotografija fotogrfija) {
        this.fotogrfija = fotogrfija;
    }

    public Fotografija getFotogrfija() {
        return fotogrfija;
    }
}
