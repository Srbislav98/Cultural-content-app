package com.tim5.culturalcontent.model;

import javax.persistence.*;

@Entity
public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer vrednost;

    public Ocena() {
    }

    public Ocena(Integer id, Integer vrednost) {
        this.id = id;
        this.vrednost = vrednost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVrednost(Integer vrednost) {
        this.vrednost = vrednost;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVrednost() {
        return vrednost;
    }
}
