package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends  JpaRepository<Admin, Integer>{
    Admin findByKorisnickoIme(String korisnickoIme);
    Admin findByKorisnickoImeAndIdNot(String korisnickoIme, Integer id);
    Admin findByEmail(String email);
}
