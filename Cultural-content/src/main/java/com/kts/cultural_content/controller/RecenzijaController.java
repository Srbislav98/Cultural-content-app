package com.kts.cultural_content.controller;


import com.kts.cultural_content.dto.RecenzijaDTO;
import com.kts.cultural_content.mapper.RecenzijaMapper;
import com.kts.cultural_content.model.Recenzija;
import com.kts.cultural_content.service.RecenzijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/recenzije", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecenzijaController {
    @Autowired
    private RecenzijaService recenzijaService;
    private RecenzijaMapper recenzijaMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RecenzijaDTO>> getAllRecenzija(){
        List<Recenzija> ocene = recenzijaService.findAll();

        return new ResponseEntity<>(torecenzijaDTOList(ocene), HttpStatus.OK);
    }

    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<RecenzijaDTO>> getAllOcenas(Pageable pageable) {
        Page<Recenzija> page = recenzijaService.findAll(pageable);
        List<RecenzijaDTO> recenzijaDTOS = torecenzijaDTOList(page.toList());
        Page<RecenzijaDTO> pageRecenzijaDTOS = new PageImpl<>(recenzijaDTOS,page.getPageable(),page.getTotalElements());

        return new ResponseEntity<>(pageRecenzijaDTOS, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<RecenzijaDTO> getRecenzija(@PathVariable Integer id){
        Recenzija recenzija = recenzijaService.findOne(id);
        if (recenzija == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recenzijaMapper.toDto(recenzija), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/create",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecenzijaDTO> createRecenzija(@RequestBody RecenzijaDTO recenzijaDTO){
        Recenzija recenzija;
        try {
            if (recenzijaDTO.getOcena()>5 || recenzijaDTO.getOcena()<1 || recenzijaDTO.getKomentar().equals(""))
                throw new Exception("Lose!");
            if(!recenzijaDTO.getFoto().equals(""))
                if(ImageIO.read(new File(recenzijaDTO.getFoto())) == null)
                    throw new Exception("Lose!");
            recenzija = recenzijaService.create(recenzijaMapper.toEntity(recenzijaDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(recenzijaMapper.toDto(recenzija), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecenzijaDTO> updateRecenzija(@RequestBody RecenzijaDTO recenzijaDTO, @PathVariable Integer id){
        Recenzija recenzija;
        try {
            if (recenzijaDTO.getOcena()>5 || recenzijaDTO.getOcena()<1 || recenzijaDTO.getKomentar().equals("") || ImageIO.read(new File(recenzijaDTO.getFoto())) == null)
                throw new Exception("Lose!");
            recenzija = recenzijaService.update(recenzijaMapper.toEntity(recenzijaDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(recenzijaMapper.toDto(recenzija), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRecenzija(@PathVariable Integer id){
        try {
            recenzijaService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public RecenzijaController() {
        recenzijaMapper = new RecenzijaMapper();
    }

    private List<RecenzijaDTO> torecenzijaDTOList(List<Recenzija> recenzijas){
        List<RecenzijaDTO> recenzijaDTOS = new ArrayList<>();
        for (Recenzija recenzija : recenzijas){
            recenzijaDTOS.add(recenzijaMapper.toDto(recenzija));
        }
        return recenzijaDTOS;
    }
}
