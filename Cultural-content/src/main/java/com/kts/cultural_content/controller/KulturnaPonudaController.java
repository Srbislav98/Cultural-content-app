package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.dto.NovostDTO;
import com.kts.cultural_content.mapper.KulturnaPonudaMapper;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Novost;
import com.kts.cultural_content.service.KulturnaPonudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

    @RequestMapping(value = "/filter-by-content/{content}", method = RequestMethod.GET)
    public ResponseEntity<List<KulturnaPonudaDTO>> getAllKulturnePonudebyContent(@PathVariable String content) {
        List<KulturnaPonuda> kulturnePonude = kulturnaPonudaService.filterByContent(content);

        return new ResponseEntity<>(toKulturnaPonudaDTOList(kulturnePonude), HttpStatus.OK);
    }

    @RequestMapping(value = "/filter-by-location/{x}/{y}", method = RequestMethod.GET)
    public ResponseEntity<List<KulturnaPonudaDTO>> getAllKulturnePonudebyLocation(@PathVariable String x, @PathVariable String y) {
        List<KulturnaPonuda> kulturnePonude = kulturnaPonudaService.filterByLocation(x, y);

        return new ResponseEntity<>(toKulturnaPonudaDTOList(kulturnePonude), HttpStatus.OK);
    }

    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<KulturnaPonudaDTO>> getAllKulturnaPonuda(Pageable pageable) {
        Page<KulturnaPonuda> page = kulturnaPonudaService.findAll(pageable);
        List<KulturnaPonudaDTO> komentarDTOS = toKulturnaPonudaDTOList(page.toList());
        Page<KulturnaPonudaDTO> pageKomentarDTOS = new PageImpl<>(komentarDTOS,page.getPageable(),page.getTotalElements());

        return new ResponseEntity<>(pageKomentarDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<KulturnaPonudaDTO> getKulturnaPonuda(@PathVariable Integer id){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(kulturnaPonudaMapper.toDto(kulturnaPonuda), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
