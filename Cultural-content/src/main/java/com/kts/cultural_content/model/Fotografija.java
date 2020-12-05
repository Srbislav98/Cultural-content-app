package com.kts.cultural_content.model;

import javax.persistence.*;

@Entity
public class Fotografija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column
    private String lokacijaFajl;

    @Column
    Integer kulId;
    @Column
    Integer komId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private KulturnaPonuda kulturnaPonuda;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn( nullable = true)
    private Komentar komentar;

    public Fotografija() {
    }

    public Fotografija(String lokacijaFajl, Integer kulId, Integer komId) {
        this.lokacijaFajl = lokacijaFajl;
        this.kulId = kulId;
        this.komId = komId;
    }

    public void setKulId(Integer kulId) {
        this.kulId = kulId;
    }

    public void setKomId(Integer komId) {
        this.komId = komId;
    }

    public Integer getKulId() {
        return kulId;
    }

    public Integer getKomId() {
        return komId;
    }

    public Integer getId() {
        return id;
    }


    public String getLokacijaFajl() {
        return lokacijaFajl;
    }

    public void setId(Integer id) {
        this.id = id;
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
