package com.tim5.culturalcontent.model;

import javax.persistence.*;

@Entity
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String vrednost;

    public Komentar(Integer id, String vrednost) {
        this.id = id;
        this.vrednost = vrednost;
    }

    public Komentar() {
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
