package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.TipKulturnePonude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipKPRepository extends JpaRepository<TipKulturnePonude, Integer> {
}
