package com.kts.cultural_content.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
public class RegistrovaniKorisnik extends Korisnik {
    public RegistrovaniKorisnik() {
    }

    public RegistrovaniKorisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, List<Uloga> uloge) {
        super(id, ime, prezime, korisnickoIme, email, lozinka, uloge);
    }
}
