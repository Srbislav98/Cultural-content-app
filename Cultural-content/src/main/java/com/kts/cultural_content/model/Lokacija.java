package com.kts.cultural_content.model;

import javax.persistence.*;

@Entity
public class Lokacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nazivLokacije;

    @Column
    private double geoDuzina;

    @Column
    private double geoSirina;

    public Lokacija() {
    }

    public Lokacija(Integer id, String nazivLokacije) {
        this.id = id;
        this.nazivLokacije = nazivLokacije;
    }

    public Lokacija(Integer id, String nazivLokacije, double geoDuzina, double geoSirina) {
        this.id = id;
        this.nazivLokacije = nazivLokacije;
        this.geoDuzina = geoDuzina;
        this.geoSirina = geoSirina;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    public double getGeoDuzina() {
        return geoDuzina;
    }

    public void setGeoDuzina(double geoDuzina) {
        this.geoDuzina = geoDuzina;
    }

    public double getGeoSirina() {
        return geoSirina;
    }

    public void setGeoSirina(double geoSirina) {
        this.geoSirina = geoSirina;
    }
}
