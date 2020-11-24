package com.kts.cultural_content.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
//@Table(name= "ADMINI")
public class Admin extends Korisnik {
    public Admin() {
    }
    public Admin(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, List<Uloga> uloge) {
        super(id, ime, prezime, korisnickoIme, email, lozinka, uloge);
    }

}
