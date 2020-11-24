package com.tim5.culturalcontent.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Novosti")
public class Novost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "opis")
    private String opis;

    @Column(name = "datum")
    private Date datum;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "Kulturna ponuda", nullable = false)
    private KulturnaPonuda kulturnaPonuda;

    public Novost() {
    }

    public Novost(Integer id, String naziv, String opis, Date datum, KulturnaPonuda kulturnaPonuda) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.datum = datum;
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public Integer getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setKulturnaPonuda(KulturnaPonuda kulturnaPonuda) {
        this.kulturnaPonuda = kulturnaPonuda;
    }

    public KulturnaPonuda getKulturnaPonuda() {
        return kulturnaPonuda;
    }
}
