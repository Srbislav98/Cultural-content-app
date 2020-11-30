package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Uloga;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UlogaDTO {

    private Integer id;
    private String ime;

    public UlogaDTO() {
    }

    public UlogaDTO(Integer id, String ime) {
        this.id = id;
        this.ime = ime;
    }

    public UlogaDTO(Uloga uloga) {
        this.id=uloga.getId();
        this.ime=uloga.getIme();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
