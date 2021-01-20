package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class KulturnaPonudaDTO implements Comparable {

    private Integer id;
    private String naziv;
    private String adresa;
    private String opis;
    private Integer idt;
    private Integer idLokacije;

    public KulturnaPonudaDTO() {
    }

    public KulturnaPonudaDTO(KulturnaPonudaDTO k){
        this.id = k.id;
        this.naziv = k.naziv;
        this.adresa = k.adresa;
        this.opis = k.opis;
        this.idt = k.idt;
        this.idLokacije = k.idLokacije;
    }

    public KulturnaPonudaDTO(Integer id, String naziv, String adresa, String opis, Integer idt, Integer idLokacije) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.opis = opis;
        this.idt = idt;
        this.idLokacije = idLokacije;
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

    public Integer getIdLokacije() {
        return idLokacije;
    }

    public void setIdLokacije(Integer idLokacije) {
        this.idLokacije = idLokacije;
    }

    public int compareTo(KulturnaPonudaDTO o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public int compareTo(Object o) {
        KulturnaPonudaDTO e = (KulturnaPonudaDTO) o;
        return getId().compareTo(e.getId());
    }
}
