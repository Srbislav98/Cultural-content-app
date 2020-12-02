package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcenaRepository extends JpaRepository<Ocena, Integer> {

    List<Ocena> findByRegistrovaniKorisnik(RegistrovaniKorisnik registrovaniKorisnik);

    List<Ocena> findByKulturnaPonuda(KulturnaPonuda kulturnaPonuda);

    List<Ocena> findByVrednost(Integer vrednost);

}
