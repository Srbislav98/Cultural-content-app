package com.kts.cultural_content.service;

import com.kts.cultural_content.model.Lokacija;
import com.kts.cultural_content.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LokacijaService {

    @Autowired
    private LokacijaRepository lokacijaRepository;

    public List<Lokacija> findAll() {
        return lokacijaRepository.findAll();
    }

    public Lokacija findByNazivLokacije(String naziv) {
        return lokacijaRepository.findByNazivLokacije(naziv);
    }

}
