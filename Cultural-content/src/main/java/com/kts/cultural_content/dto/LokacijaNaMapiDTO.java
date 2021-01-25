package com.kts.cultural_content.dto;

public class LokacijaNaMapiDTO {

    private double geoDuzina;
    private double geoSirina;

    public LokacijaNaMapiDTO() {
    }

    public LokacijaNaMapiDTO(double geoDuzina, double geoSirina) {
        this.geoDuzina = geoDuzina;
        this.geoSirina = geoSirina;
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
