package com.tim5.culturalcontent.model;

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

    public Fotografija() {
    }

    public Fotografija(Integer id, String naziv, String lokacijaFajl) {
        this.id = id;
        this.naziv = naziv;
        this.lokacijaFajl = lokacijaFajl;
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
}
