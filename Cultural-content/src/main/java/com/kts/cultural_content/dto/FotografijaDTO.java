package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.model.KulturnaPonuda;

public class FotografijaDTO {

    private Integer id;
    private String lokacijaFajl;
    Integer kulId;
    Integer komId;


    public FotografijaDTO(Integer id, String lokacijaFajl, Integer kulId, Integer komId) {
        this.id = id;
        this.lokacijaFajl = lokacijaFajl;
        this.kulId = kulId;
        this.komId = komId;
    }

    public void setKulId(Integer kulId) {
        this.kulId = kulId;
    }

    public void setKomId(Integer komId) {
        this.komId = komId;
    }

    public Integer getKulId() {
        return kulId;
    }

    public Integer getKomId() {
        return komId;
    }

    public FotografijaDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public void setLokacijaFajl(String lokacijaFajl) {
        this.lokacijaFajl = lokacijaFajl;
    }



    public Integer getId() {
        return id;
    }



    public String getLokacijaFajl() {
        return lokacijaFajl;
    }


}
