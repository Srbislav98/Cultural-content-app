package com.kts.cultural_content.model;

import com.kts.cultural_content.dto.UlogaDTO;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
//@Table(name="ULOGE")
public class Uloga implements GrantedAuthority {

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

    @Override
    public String getAuthority() {
        return ime;
    }

    @Override
    public boolean equals(Object obj) {
        Uloga o = (Uloga) obj;
        return this.getId().equals(o.getId()) && this.getIme().equals((o.getIme()));
    }
}
