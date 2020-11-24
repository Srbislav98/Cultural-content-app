package com.tim5.culturalcontent.repository;

import com.tim5.culturalcontent.model.TipKulturnePonude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipKPRepository extends JpaRepository<TipKulturnePonude, Long> {
}
