package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;

import java.util.HashSet;
import java.util.Set;

public class KomentarDTO {
    private Integer id;

    private String vrednost;



    public KomentarDTO() {
    }

    public KomentarDTO(Integer id, String vrednost) {
        this.id = id;
        this.vrednost = vrednost;

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
