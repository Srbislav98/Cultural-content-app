package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcenaRepository extends JpaRepository<Ocena, Integer> {

    List<Ocena> findByRegistrovaniKorisnik(string email);

    List<Ocena> findByKulturnaPonuda(string naziv);

    List<Ocena> findByVrednost(Integer vrednost);

}
