package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.NovostDTO;
import com.kts.cultural_content.mapper.NovostMapper;
import com.kts.cultural_content.model.Novost;
import com.kts.cultural_content.repository.NovostRepository;
import com.kts.cultural_content.service.NovostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/novosti", produces = MediaType.APPLICATION_JSON_VALUE)
public class NovostController {

    @Autowired
    private NovostService novostService;
    private NovostMapper novostMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<NovostDTO>> getAllNovosti() {
        List<Novost> novosti = novostService.findAll();

        return new ResponseEntity<>(toNovostDTOList(novosti), HttpStatus.OK);
    }

    private List<NovostDTO> toNovostDTOList(List<Novost> novosti) {
        List<NovostDTO> novostDTOS = new ArrayList<>();
        for (Novost novost: novosti) {
            novostDTOS.add(novostMapper.toDto(novost));
        }
        return novostDTOS;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<NovostDTO> getNovost(@PathVariable Integer id){
        Novost novost = novostService.findOne(id);
        if(novost == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(novostMapper.toDto(novost), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NovostDTO> createNovost(@RequestBody NovostDTO novostDTO){
        Novost novost;
        try {
            novost = novostService.create(novostMapper.toEntity(novostDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(novostMapper.toDto(novost), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NovostDTO> updateNovost(
            @RequestBody NovostDTO novostDTO, @PathVariable Integer id){
        Novost novost;
        try {
            novost = novostService.update(novostMapper.toEntity(novostDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(novostMapper.toDto(novost), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteNovost(@PathVariable Integer id){
        try {
            novostService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
