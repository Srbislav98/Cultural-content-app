package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KulturnaPonudaRepository extends JpaRepository<KulturnaPonuda, Integer> {
    List<KulturnaPonuda> findByNaziv(String naziv);
    List<KulturnaPonuda> findByAdmin(Admin admin);
    List<KulturnaPonuda> findByLokacija(Lokacija lokacija);
    List<KulturnaPonuda> findDistinctByNazivContainingOrOpisContainingOrderByNaziv(String naziv, String opis);
}
