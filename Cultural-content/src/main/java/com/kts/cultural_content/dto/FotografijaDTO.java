package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.KulturnaPonuda;

public class FotografijaDTO {

    private Integer id;
    private String lokacijaFajl;
    Integer kulId;
    Integer recId;


    public FotografijaDTO(Integer id, String lokacijaFajl, Integer kulId, Integer recId) {
        this.id = id;
        this.lokacijaFajl = lokacijaFajl;
        this.kulId = kulId;
        this.recId = recId;
    }

    public void setKulId(Integer kulId) {
        this.kulId = kulId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public Integer getKulId() {
        return kulId;
    }

    public Integer getRecId() {
        return recId;
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
