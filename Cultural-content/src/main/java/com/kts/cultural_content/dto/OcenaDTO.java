package com.kts.cultural_content.dto;



public class OcenaDTO {

    private Integer id;
    private Integer vrednost;


    public OcenaDTO() {
    }

    public OcenaDTO(Integer id,Integer vrednost) {
        this.id = id;
        this.vrednost = vrednost;

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
