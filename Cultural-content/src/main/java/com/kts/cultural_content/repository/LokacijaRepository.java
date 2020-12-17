package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LokacijaRepository extends JpaRepository<Lokacija, Integer> {
    Lokacija findByNazivLokacije(String naziv);
}
