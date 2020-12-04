package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.FotografijaDTO;
import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.mapper.FotografijaMapper;
import com.kts.cultural_content.mapper.OcenaMapper;
import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.service.FotografijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/fotografije", produces = MediaType.APPLICATION_JSON_VALUE)
public class FotografijaController {

    @Autowired
    private FotografijaService fotografijaService;
    private FotografijaMapper fotografijaMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FotografijaDTO>> getAllFotografija(){
        List<Fotografija> fotografijas = fotografijaService.findAll();

        return new ResponseEntity<>(toFotografijaDTOList(fotografijas), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<FotografijaDTO> getFotografija(@PathVariable Integer id){
        Fotografija fotografija = fotografijaService.findOne(id);
        if (fotografija == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fotografijaMapper.toDto(fotografija), HttpStatus.OK);
    }

    @RequestMapping(value = "/create",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotografijaDTO> createFotografija(@RequestBody FotografijaDTO fotografijaDTO){
        Fotografija fotografija;
        try {
            fotografija = fotografijaService.create(fotografijaMapper.toEntity(fotografijaDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(fotografijaMapper.toDto(fotografija), HttpStatus.CREATED);
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotografijaDTO> updateFotografija(@RequestBody FotografijaDTO fotografijaDTO, @PathVariable Integer id){
        Fotografija fotografija;
        try {
            fotografija = fotografijaService.update(fotografijaMapper.toEntity(fotografijaDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(fotografijaMapper.toDto(fotografija), HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFotografija(@PathVariable Integer id){
        try {
            fotografijaService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public FotografijaController() {
        fotografijaMapper = new FotografijaMapper();
    }

    private List<FotografijaDTO> toFotografijaDTOList(List<Fotografija> fotografijas){
        List<FotografijaDTO> fotografijaDTOS = new ArrayList<>();
        for (Fotografija fotografija : fotografijas){
            fotografijaDTOS.add(fotografijaMapper.toDto(fotografija));
        }
        return fotografijaDTOS;
    }

}
