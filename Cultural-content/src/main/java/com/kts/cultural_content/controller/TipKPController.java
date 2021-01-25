package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.dto.TipKulturnePonudeDTO;
import com.kts.cultural_content.mapper.KulturnaPonudaMapper;
import com.kts.cultural_content.mapper.TipKulturnePonudeMapper;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.TipKulturnePonude;
import com.kts.cultural_content.service.KulturnaPonudaService;
import com.kts.cultural_content.service.TipKPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tipoviKP", produces = MediaType.APPLICATION_JSON_VALUE)
public class TipKPController {

    @Autowired
    private TipKPService tipKulturnePonudeService;
    private TipKulturnePonudeMapper tipKulturnePonudeMapper;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TipKulturnePonudeDTO>> getAllTipKulturnePonude(){
        List<TipKulturnePonude> tipKulturnePonudes = tipKulturnePonudeService.findAll();

        return new ResponseEntity<>(toTipKulturnePonudeDTOList(tipKulturnePonudes), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<TipKulturnePonudeDTO>> getAllKulturnaPonuda(Pageable pageable) {
        Page<TipKulturnePonude> page = tipKulturnePonudeService.findAll(pageable);
        List<TipKulturnePonudeDTO> komentarDTOS = toTipKulturnePonudeDTOList(page.toList());
        Page<TipKulturnePonudeDTO> pageKomentarDTOS = new PageImpl<>(komentarDTOS,page.getPageable(),page.getTotalElements());

        return new ResponseEntity<>(pageKomentarDTOS, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<TipKulturnePonudeDTO> getTipKulturnePonude(@PathVariable Integer id){
        TipKulturnePonude tipKulturnePonude = tipKulturnePonudeService.findOne(id);
        if (tipKulturnePonude == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tipKulturnePonudeMapper.toDto(tipKulturnePonude), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipKulturnePonudeDTO> createTipKulturnePonude(@RequestBody TipKulturnePonudeDTO tipKulturnePonudeDTO){
        TipKulturnePonude tipKulturnePonude;
        try {
            tipKulturnePonude = tipKulturnePonudeService.create(tipKulturnePonudeMapper.toEntity(tipKulturnePonudeDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(tipKulturnePonudeMapper.toDto(tipKulturnePonude), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipKulturnePonudeDTO> updateTipKulturnePonude(@RequestBody TipKulturnePonudeDTO tipKulturnePonudeDTO, @PathVariable Integer id){
        TipKulturnePonude tipKulturnePonude;
        try {
            tipKulturnePonude = tipKulturnePonudeService.update(tipKulturnePonudeMapper.toEntity(tipKulturnePonudeDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(tipKulturnePonudeMapper.toDto(tipKulturnePonude), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTipKulturnePonude(@PathVariable Integer id){
        try {
            tipKulturnePonudeService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public TipKPController() {
        tipKulturnePonudeMapper = new TipKulturnePonudeMapper();
    }

    private List<TipKulturnePonudeDTO> toTipKulturnePonudeDTOList(List<TipKulturnePonude> tipKulturnePonudes){
        List<TipKulturnePonudeDTO> tipKulturnePonudeDTOs = new ArrayList<>();
        for (TipKulturnePonude tipKulturnePonude : tipKulturnePonudes){
            tipKulturnePonudeDTOs.add(tipKulturnePonudeMapper.toDto(tipKulturnePonude));
        }
        return tipKulturnePonudeDTOs;
    }
}
