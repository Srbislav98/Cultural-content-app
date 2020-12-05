package com.kts.cultural_content.model;

import com.kts.cultural_content.dto.UlogaDTO;

import javax.persistence.*;

@Entity
//@Table(name="ULOGE")
public class Uloga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column
    private String ime;

    public Uloga() {

    }

    public Uloga(Integer id, String ime) {
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
