package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.mapper.KulturnaPonudaMapper;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.service.KulturnaPonudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/kulturnePonude", produces = MediaType.APPLICATION_JSON_VALUE)
public class KulturnaPonudaController {

    @Autowired
    private KulturnaPonudaService kulturnaPonudaService;
    private KulturnaPonudaMapper kulturnaPonudaMapper;



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<KulturnaPonudaDTO>> getAllKulturnaPonuda(){
        List<KulturnaPonuda> kulturnePonude = kulturnaPonudaService.findAll();

        return new ResponseEntity<>(toKulturnaPonudaDTOList(kulturnePonude), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<KulturnaPonudaDTO> getKulturnaPonuda(@PathVariable Integer id){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(kulturnaPonudaMapper.toDto(kulturnaPonuda), HttpStatus.OK);
    }

    @RequestMapping(value="/create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KulturnaPonudaDTO> createKulturnaPonuda(@RequestBody KulturnaPonudaDTO kulturnaPonudaDTO){
        KulturnaPonuda kulturnaPonuda;
        try {
            kulturnaPonuda = kulturnaPonudaService.create(kulturnaPonudaMapper.toEntity(kulturnaPonudaDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(kulturnaPonudaMapper.toDto(kulturnaPonuda), HttpStatus.CREATED);
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KulturnaPonudaDTO> updateKulturnaPonuda(@RequestBody KulturnaPonudaDTO kulturnaPonudaDTO, @PathVariable Integer id){
        KulturnaPonuda kulturnaPonuda;
        try {
            kulturnaPonuda = kulturnaPonudaService.update(kulturnaPonudaMapper.toEntity(kulturnaPonudaDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(kulturnaPonudaMapper.toDto(kulturnaPonuda), HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteKulturnaPonuda(@PathVariable Integer id){
        try {
            kulturnaPonudaService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public KulturnaPonudaController() {
        kulturnaPonudaMapper = new KulturnaPonudaMapper();
    }

    private List<KulturnaPonudaDTO> toKulturnaPonudaDTOList(List<KulturnaPonuda> kulturnaPonude){
        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = new ArrayList<>();
        for (KulturnaPonuda kulturnaPonuda : kulturnaPonude){
            kulturnaPonudaDTOS.add(kulturnaPonudaMapper.toDto(kulturnaPonuda));
        }
        return kulturnaPonudaDTOS;
    }
}
