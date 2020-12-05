package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;


public class OcenaDTO {

    private Integer id;
    private Integer vrednost;

    private Integer regId;


    private Integer kulId;

    public OcenaDTO() {
    }

    public OcenaDTO(Integer id, Integer vrednost, Integer regId, Integer kulId) {
        this.id = id;
        this.vrednost = vrednost;
        this.regId = regId;
        this.kulId = kulId;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public void setKulId(Integer kulId) {
        this.kulId = kulId;
    }

    public Integer getRegId() {
        return regId;
    }

    public Integer getKulId() {
        return kulId;
    }

    public void setVrednost(Integer vrednost) {
        this.vrednost = vrednost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }





    public Integer getVrednost() {
        return vrednost;
    }


}
