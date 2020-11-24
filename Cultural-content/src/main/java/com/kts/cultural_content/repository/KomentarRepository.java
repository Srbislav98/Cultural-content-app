package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarRepository extends JpaRepository<Komentar, Integer> {
}
