package com.kts.cultural_content.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class TipKulturnePonude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//@Column(nullable=false, unique = true)//
    private Integer id;

    @Column(nullable=false, unique = true)
    private String naziv;

    @OneToMany(mappedBy = "tipKulturnePonude", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<KulturnaPonuda> kulturnaPonudas = new HashSet<>();

    public TipKulturnePonude() {
    }

    public TipKulturnePonude(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
        kulturnaPonudas = new HashSet<>();
    }

    public Set<KulturnaPonuda> getKulturnaPonudas() {
        return kulturnaPonudas;
    }

    public void setKulturnaPonudas(Set<KulturnaPonuda> kulturnaPonudas) {
        this.kulturnaPonudas = kulturnaPonudas;
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
