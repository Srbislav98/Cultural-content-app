package com.kts.cultural_content.dto;

import java.util.Date;

public class NovostDTO {

    private Integer id;
    private String naziv;
    private String opis;
    private Date datum;

    public NovostDTO() {
    }

    public NovostDTO(Integer id, String naziv, String opis, Date datum) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.datum = datum;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

}
