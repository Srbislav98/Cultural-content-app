package com.kts.cultural_content.model;

import javax.persistence.*;

@Entity
public class Fotografija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String naziv;

    @Column
    private String lokacijaFajl;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private KulturnaPonuda kulturnaPonuda;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private Komentar komentar;

    public Fotografija() {
    }

    public Fotografija(Integer id, String naziv, String lokacijaFajl, KulturnaPonuda kulturnaPonuda, Komentar komentar) {
        this.id = id;
        this.naziv = naziv;
        this.lokacijaFajl = lokacijaFajl;
        this.kulturnaPonuda = kulturnaPonuda;
        this.komentar = komentar;
    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getLokacijaFajl() {
        return lokacijaFajl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setLokacijaFajl(String lokacijaFajl) {
        this.lokacijaFajl = lokacijaFajl;
    }

    public void setKulturnaPonuda(KulturnaPonuda kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public void setKomentar(Komentar komentar) {
        this.komentar = komentar;
    }

    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }

    public Komentar getKomentar() {
        return komentar;
    }
}