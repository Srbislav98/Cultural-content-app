package com.kts.cultural_content.dto;

public class LokacijaDTO {

    private Integer id;
    private String nazivLokacije;
    private double geoDuzina;
    private double geoSirina;

    public LokacijaDTO() {
    }

    public LokacijaDTO(Integer id, String nazivLokacije, double geoDuzina, double geoSirina) {
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

    public double getGeoSirina() {
        return geoSirina;
    }

    public void setGeoSirina(double geoSirina) {
        this.geoSirina = geoSirina;
    }

    public double getGeoDuzina() {
        return geoDuzina;
    }

    public void setGeoDuzina(double geoDuzina) {
        this.geoDuzina = geoDuzina;
    }
}
