package com.kts.cultural_content.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class RegistrovaniKorisnik extends Korisnik {
    public RegistrovaniKorisnik() {
    }

    public RegistrovaniKorisnik(Integer id, String ime, String prezime, String korisnickoIme, String email, String lozinka, Uloga uloga) {
        super(id, ime, prezime, korisnickoIme, email, lozinka, uloga);
    }
}
