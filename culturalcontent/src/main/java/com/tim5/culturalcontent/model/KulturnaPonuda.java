package com.tim5.culturalcontent.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "KULTURNAPONUDA")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class KulturnaPonuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//@Column(nullable=false, unique = true)//
    private Integer id;

    @Column(nullable=false, unique = true)
    private String naziv;

    @Column(nullable=false)
    private String geoSirina;

    @Column(nullable=false)
    private String geoDuzina;

    @Column(nullable=false)
    private String adresa;

    @Column(nullable=false)
    private String opis;

    public KulturnaPonuda() {
    }

    public KulturnaPonuda(Integer id, String naziv, String geoSirina, String geoDuzina, String adresa, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.geoSirina = geoSirina;
        this.geoDuzina = geoDuzina;
        this.adresa = adresa;
        this.opis = opis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGeoSirina() {
        return geoSirina;
    }

    public void setGeoSirina(String geoSirina) {
        this.geoSirina = geoSirina;
    }

    public String getGeoDuzina() {
        return geoDuzina;
    }

    public void setGeoDuzina(String geoDuzina) {
        this.geoDuzina = geoDuzina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOpis() {
        return opis;
    }

	public void setOpis(String opis) {
        this.opis = opis;
    }
}
