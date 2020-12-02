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
    private Set<Fotografija> fotogrfija = new HashSet<>();
    private Admin admin;
    private TipKulturnePonude tipKulturnePonude;
    private ArrayList<RegistrovaniKorisnik> registrovaniKorisnik = new ArrayList<>();

    public KulturnaPonudaDTO() {
    }
    public KulturnaPonudaDTO(KulturnaPonudaDTO k){
        this.id = k.id;
        this.naziv = k.naziv;
        this.geoSirina = k.geoSirina;
        this.geoDuzina = k.geoDuzina;
        this.adresa = k.adresa;
        this.opis = k.opis;
        this.admin = k.admin;
        this.tipKulturnePonude = k.tipKulturnePonude;
        this.fotogrfija = k.fotogrfija;
        this.registrovaniKorisnik = k.registrovaniKorisnik;
    }

    public KulturnaPonudaDTO(Integer id, String naziv, String geoSirina, String geoDuzina, String adresa, String opis, Admin admin) {
        this.id = id;
        this.naziv = naziv;
        this.geoSirina = geoSirina;
        this.geoDuzina = geoDuzina;
        this.adresa = adresa;
        this.opis = opis;
        this.admin = admin;
        this.tipKulturnePonude = tipKulturnePonude;
        this.fotogrfija = new HashSet<>();
        this.registrovaniKorisnik = new ArrayList<>();
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

    public Set<Fotografija> getFotogrfija() {
        return fotogrfija;
    }

    public void setFotogrfija(Set<Fotografija> fotogrfija) {
        this.fotogrfija = fotogrfija;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public TipKulturnePonude getTipKulturnePonude() {
        return tipKulturnePonude;
    }

    public void setTipKulturnePonude(TipKulturnePonude tipKulturnePonude) {
        this.tipKulturnePonude = tipKulturnePonude;
    }

    public int compareTo(KulturnaPonudaDTO o) {
        return this.id.compareTo(o.id);
    }
}
