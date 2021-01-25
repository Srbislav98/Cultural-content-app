package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.LokacijaDTO;
import com.kts.cultural_content.dto.LokacijaNaMapiDTO;
import com.kts.cultural_content.mapper.LokacijaMapper;
import com.kts.cultural_content.model.Lokacija;
import com.kts.cultural_content.service.LokacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/lokacije")
public class LokacijaController {

    @Autowired
    private LokacijaService lokacijaService;
    private LokacijaMapper lokacijaMapper;

    public LokacijaController() {
        lokacijaMapper = new LokacijaMapper();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<LokacijaDTO>> getAllLokacije(){
        List<Lokacija> tipKulturnePonudes = lokacijaService.findAll();

        return new ResponseEntity<>(toLokacijaDTOList(tipKulturnePonudes), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByNaziv/{naziv}", method = RequestMethod.GET)
    public ResponseEntity<LokacijaDTO> getLokacijaPoNazivu(@PathVariable String naziv){
        Lokacija lokacija = lokacijaService.findByNazivLokacije(naziv);
        if (lokacija == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lokacijaMapper.toDto(lokacija), HttpStatus.OK);
    }

    private List<LokacijaDTO> toLokacijaDTOList(List<Lokacija> tipKulturnePonudes){
        List<LokacijaDTO> tipKulturnePonudeDTOs = new ArrayList<>();
        for (Lokacija tipKulturnePonude : tipKulturnePonudes){
            tipKulturnePonudeDTOs.add(lokacijaMapper.toDto(tipKulturnePonude));
        }
        return tipKulturnePonudeDTOs;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LokacijaDTO> createLokacija(@RequestBody LokacijaDTO lokacijaDTO){
        Lokacija lokacija;
        try {
            lokacija = lokacijaService.createLokacija(lokacijaMapper.toEntity(lokacijaDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(lokacijaMapper.toDto(lokacija), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getLocationsIds", method = RequestMethod.GET)
    public ResponseEntity<List<LokacijaNaMapiDTO>> getMapLocationsByIds(@RequestParam List<Integer> ids) {
        List<LokacijaNaMapiDTO> lokacijaNaMapiDTOS = new ArrayList<>();
        for(int id : ids) {
            Lokacija lokacija = lokacijaService.findOne(id);
            LokacijaNaMapiDTO lokacijaNaMapiDTO = new LokacijaNaMapiDTO(lokacija.getGeoDuzina(), lokacija.getGeoSirina());
            lokacijaNaMapiDTOS.add(lokacijaNaMapiDTO);
        }
        return new ResponseEntity<>(lokacijaNaMapiDTOS, HttpStatus.OK);
    }

}
