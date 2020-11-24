package com.tim5.culturalcontent.repository;

import com.tim5.culturalcontent.model.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarRepository extends JpaRepository<Komentar, Integer> {
}
