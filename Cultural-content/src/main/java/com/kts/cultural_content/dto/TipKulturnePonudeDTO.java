package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TipKulturnePonudeDTO {
    private Integer id;
    private String naziv;

    public TipKulturnePonudeDTO() {
    }
    public TipKulturnePonudeDTO(TipKulturnePonudeDTO k){
        this.id = k.id;
        this.naziv = k.naziv;
    }

    public TipKulturnePonudeDTO(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
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

    public int compareTo(TipKulturnePonudeDTO o) {
        return this.id.compareTo(o.id);
    }
}
