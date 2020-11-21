package com.tim5.culturalcontent.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
//@Table(name= "REGISTROVANI_KORISNICI")
public class RegistrovaniKorisnik extends Korisnik {
    public RegistrovaniKorisnik() {
    }

    public RegistrovaniKorisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, List<Uloga> uloge) {
        super(id, ime, prezime, korisnickoIme, email, lozinka, uloge);
    }
}
