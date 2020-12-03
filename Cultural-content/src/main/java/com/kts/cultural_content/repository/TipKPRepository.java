package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.TipKulturnePonude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipKPRepository extends JpaRepository<TipKulturnePonude, Integer> {
    List<TipKulturnePonude> findByNaziv(String naziv);
}
