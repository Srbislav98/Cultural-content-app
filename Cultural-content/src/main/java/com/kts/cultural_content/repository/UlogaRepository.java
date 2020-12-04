package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.TipKulturnePonude;
import com.kts.cultural_content.model.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UlogaRepository extends JpaRepository<Uloga, Integer> {
    List<Uloga> findByIme(String role_user);
}
