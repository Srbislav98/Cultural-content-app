package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.LokacijaDTO;
import com.kts.cultural_content.mapper.LokacijaMapper;
import com.kts.cultural_content.model.Lokacija;
import com.kts.cultural_content.service.LokacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/lokacije")
public class LokacijaController {

    @Autowired
    private LokacijaService lokacijaService;
    private LokacijaMapper lokacijaMapper;

    public LokacijaController() {
        lokacijaMapper = new LokacijaMapper();
    }

    @RequestMapping(value = "/getByNaziv/{naziv}", method = RequestMethod.GET)
    public ResponseEntity<LokacijaDTO> getLokacijaPoNazivu(@PathVariable String naziv){
        Lokacija lokacija = lokacijaService.findByNazivLokacije(naziv);
        if (lokacija == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lokacijaMapper.toDto(lokacija), HttpStatus.OK);
    }

}
