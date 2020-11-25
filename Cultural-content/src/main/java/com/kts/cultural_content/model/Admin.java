package com.kts.cultural_content.model;

import javax.persistence.Entity;

@Entity
//@Table(name= "ADMINI")
public class Admin extends Korisnik {
    public Admin() {
    }
    public Admin(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, Uloga uloga) {
        super(id, ime, prezime, korisnickoIme, email, lozinka, uloga);
    }

}
