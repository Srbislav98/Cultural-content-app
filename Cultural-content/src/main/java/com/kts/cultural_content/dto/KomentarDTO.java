package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Fotografija;


public class KomentarDTO {
    private Integer id;

    private String vrednost;
    private Integer regId;
    private Integer kulId;




    public KomentarDTO() {
    }

    public KomentarDTO(Integer id, String vrednost, Integer regId, Integer kulId) {
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



    public void setId(Integer id) {
        this.id = id;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }



    public Integer getId() {
        return id;
    }

    public String getVrednost() {
        return vrednost;
    }


}
