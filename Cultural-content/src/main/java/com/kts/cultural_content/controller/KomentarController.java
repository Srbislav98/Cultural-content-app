package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.KomentarDTO;
import com.kts.cultural_content.dto.OcenaDTO;
import com.kts.cultural_content.mapper.KomentarMapper;
import com.kts.cultural_content.mapper.OcenaMapper;
import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.model.Ocena;
import com.kts.cultural_content.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/komentari", produces = MediaType.APPLICATION_JSON_VALUE)
public class KomentarController {

    @Autowired
    private KomentarService komentarService;
    private KomentarMapper komentarMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<KomentarDTO>> getAllKomentar(){
        List<Komentar> ocene = komentarService.findAll();

        return new ResponseEntity<>(tokomentarDTOList(ocene), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<KomentarDTO> getKomentar(@PathVariable Integer id){
        Komentar komentar = komentarService.findOne(id);
        if (komentar == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(komentarMapper.toDto(komentar), HttpStatus.OK);
    }

    @RequestMapping(value = "/create",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KomentarDTO> createKomentar(@RequestBody KomentarDTO komentarDTO){
        Komentar komentar;
        try {
            komentar = komentarService.create(komentarMapper.toEntity(komentarDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(komentarMapper.toDto(komentar), HttpStatus.CREATED);
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KomentarDTO> updateKomentar(@RequestBody KomentarDTO komentarDTO, @PathVariable Integer id){
        Komentar komentar;
        try {
            komentar = komentarService.update(komentarMapper.toEntity(komentarDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(komentarMapper.toDto(komentar), HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteKomentar(@PathVariable Integer id){
        try {
            komentarService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public KomentarController() {
        komentarMapper = new KomentarMapper();
    }

    private List<KomentarDTO> tokomentarDTOList(List<Komentar> komentars){
        List<KomentarDTO> komentarDTOS = new ArrayList<>();
        for (Komentar komentar : komentars){
            komentarDTOS.add(komentarMapper.toDto(komentar));
        }
        return komentarDTOS;
    }

}
