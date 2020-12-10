package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KulturnaPonudaRepository extends JpaRepository<KulturnaPonuda, Integer> {
    List<KulturnaPonuda> findByNaziv(String naziv);
    List<KulturnaPonuda> findByAdmin(Admin admin);
    List<KulturnaPonuda> findByNazivContainingOrderByNaziv(String slova);
    List<KulturnaPonuda> findByOpisContainingOrderById(String rec);
    List<KulturnaPonuda> findByGeoSirinaAndGeoDuzinaOrderByNaziv(String x, String y);
}
