package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.Uloga;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class UlogaDTO {

    private Integer id;
    @NotBlank(message = "Ime cannot be empty.")
    private String ime;

    public UlogaDTO() {
    }

    public UlogaDTO(Integer id,@NotBlank(message = "Ime cannot be empty.") String ime) {
        this.id = id;
        this.ime = ime;
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
