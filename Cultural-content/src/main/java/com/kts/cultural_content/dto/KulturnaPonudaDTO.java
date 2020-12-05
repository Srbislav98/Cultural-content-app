package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class KulturnaPonudaDTO {

    private Integer id;
    private String naziv;
    private String geoSirina;
    private String geoDuzina;
    private String adresa;
    private String opis;
    private Integer idt;

    public KulturnaPonudaDTO() {
    }
    public KulturnaPonudaDTO(KulturnaPonudaDTO k){
        this.id = k.id;
        this.naziv = k.naziv;
        this.geoSirina = k.geoSirina;
        this.geoDuzina = k.geoDuzina;
        this.adresa = k.adresa;
        this.opis = k.opis;
        this.idt = k.idt;
    }

    public KulturnaPonudaDTO(Integer id, String naziv, String geoSirina, String geoDuzina, String adresa, String opis, Integer idt) {
        this.id = id;
        this.naziv = naziv;
        this.geoSirina = geoSirina;
        this.geoDuzina = geoDuzina;
        this.adresa = adresa;
        this.opis = opis;
        this.idt = idt;
    }

    public Integer getIdt() {
        return idt;
    }

    public void setIdt(Integer idt) {
        this.idt = idt;
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

    public int compareTo(KulturnaPonudaDTO o) {
        return this.id.compareTo(o.id);
    }
}
