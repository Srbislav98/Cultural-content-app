package com.kts.cultural_content.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class TipKulturnePonude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//@Column(nullable=false, unique = true)//
    private Integer id;

    @Column(nullable=false, unique = true)
    private String naziv;

    public TipKulturnePonude() {
    }

    public TipKulturnePonude(Integer id, String naziv) {
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
}
