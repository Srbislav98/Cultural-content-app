package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.model.KulturnaPonuda;

public class FotografijaDTO {

    private Integer id;
    private String lokacijaFajl;


    public FotografijaDTO(Integer id, String lokacijaFajl) {
        this.id = id;
        this.lokacijaFajl = lokacijaFajl;

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
