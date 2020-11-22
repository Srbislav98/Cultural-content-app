package com.tim5.culturalcontent.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Novost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String naziv;

    @Column
    private String opis;

    @Column
    private Date datum;

    public Novost() {
    }

    public Novost(Integer id, String naziv, String opis, Date datum) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.datum = datum;
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
}
