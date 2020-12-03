package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.RegistrovaniKorisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrovaniKorisnikRepository extends JpaRepository<RegistrovaniKorisnik, Integer> {
    RegistrovaniKorisnik findByKorisnickoIme(String korisnickoIme);
    RegistrovaniKorisnik findByKorisnickoImeAndIdNot(String korisnickoIme, Integer id);
}
