package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.LokacijaDTO;
import com.kts.cultural_content.dto.LokacijaNaMapiDTO;
import com.kts.cultural_content.mapper.LokacijaMapper;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Lokacija;
import com.kts.cultural_content.service.KulturnaPonudaService;
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
    @Autowired
    private KulturnaPonudaService kulturnaPonudaService;
    private LokacijaMapper lokacijaMapper;

    public LokacijaController() {
        lokacijaMapper = new LokacijaMapper();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<LokacijaDTO>> getAllLokacije(){
        List<Lokacija> lokacije = lokacijaService.findAll();

        return new ResponseEntity<>(toLokacijaDTOList(lokacije), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByNaziv/{naziv}", method = RequestMethod.GET)
    public ResponseEntity<LokacijaDTO> getLokacijaPoNazivu(@PathVariable String naziv){
        Lokacija lokacija = lokacijaService.findByNazivLokacije(naziv);
        if (lokacija == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lokacijaMapper.toDto(lokacija), HttpStatus.OK);
    }

    private List<LokacijaDTO> toLokacijaDTOList(List<Lokacija> lokacije){
        List<LokacijaDTO> lokacijeDTOs = new ArrayList<>();
        for (Lokacija lokacija : lokacije){
            lokacijeDTOs.add(lokacijaMapper.toDto(lokacija));
        }
        return lokacijeDTOs;
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<LokacijaDTO> getLokacijaPoIdKulturnePonude(@PathVariable Integer id) {
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        Lokacija lokacija = lokacijaService.findOne(kulturnaPonuda.getLokacija().getId());
        if (kulturnaPonuda == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lokacijaMapper.toDto(lokacija), HttpStatus.OK);
    }

    @PostMapping(value = "/getLocationsIds")
    public ResponseEntity<List<LokacijaNaMapiDTO>> getMapLocationsByIds(@RequestBody List<Integer> ids) {
        List<LokacijaNaMapiDTO> lokacijaNaMapiDTOS = new ArrayList<>();
        for(int id : ids) {
            Lokacija lokacija = lokacijaService.findOne(id);
            LokacijaNaMapiDTO lokacijaNaMapiDTO = new LokacijaNaMapiDTO(lokacija.getGeoDuzina(), lokacija.getGeoSirina());
            lokacijaNaMapiDTOS.add(lokacijaNaMapiDTO);
        }
        return new ResponseEntity<>(lokacijaNaMapiDTOS, HttpStatus.OK);
    }

}
