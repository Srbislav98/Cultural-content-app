package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.helper.OcenaMapper;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.service.OcenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ocene", produces = MediaType.APPLICATION_JSON_VALUE)
public class OcenaController {

    @Autowired
    private OcenaService ocenaService;

    private OcenaMapper ocenaMapper;



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OcenaDTO>> getAllOcena(){
        List<Ocena> ocene = ocenaService.findAll();

        return new ResponseEntity<>(toOcenaDTOList(ocene), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OcenaDTO> getOcena(@PathVariable Integer id){
        Ocena ocena = ocenaService.findOne(id);
        if (ocena == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ocenaMapper.toDto(ocena), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OcenaDTO> createOcena(@RequestBody OcenaDTO ocenaDTO){
        Ocena ocena;
        try {
            ocena = ocenaService.create(ocenaMapper.toEntity(ocenaDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ocenaMapper.toDto(ocena), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OcenaDTO> updateOcena(@RequestBody OcenaDTO ocenaDTO, @PathVariable Integer id){
        Ocena ocena;
        try {
            ocena = ocenaService.update(ocenaMapper.toEntity(ocenaDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ocenaMapper.toDto(ocena), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOcena(@PathVariable Integer id){
        try {
            ocenaService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public OcenaController() {
        ocenaMapper = new OcenaMapper();
    }

    private List<OcenaDTO> toOcenaDTOList(List<Ocena> ocene){
        List<OcenaDTO> ocenaDTOS = new ArrayList<>();
        for (Ocena ocena : ocene){
            ocenaDTOS.add(ocenaMapper.toDto(ocena));
        }
        return ocenaDTOS;
    }

}
