package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.NovostDTO;
import com.kts.cultural_content.mapper.NovostMapper;
import com.kts.cultural_content.model.Novost;
import com.kts.cultural_content.service.NovostService;
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

    public NovostController() {
        novostMapper = new NovostMapper();
    }

    private List<NovostDTO> toNovostDTOList(List<Novost> novosti) {
        List<NovostDTO> novostDTOS = new ArrayList<>();
        for (Novost novost: novosti) {
            novostDTOS.add(novostMapper.toDto(novost));
        }
        return novostDTOS;
    }

    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<NovostDTO>> getAllNovosti(Pageable pageable) {
        Page<Novost> page = novostService.findAll(pageable);
        List<NovostDTO> novostDTOS = toNovostDTOList(page.toList());
        Page<NovostDTO> pageNovostDTOS = new PageImpl<>(novostDTOS,page.getPageable(),page.getTotalElements());

        return new ResponseEntity<>(pageNovostDTOS, HttpStatus.OK);
    }

    @RequestMapping(value="/get/{id}", method=RequestMethod.GET)
    public ResponseEntity<NovostDTO> getNovost(@PathVariable Integer id){
        Novost novost = novostService.findOne(id);
        if(novost == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(novostMapper.toDto(novost), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NovostDTO> createNovost(@RequestBody NovostDTO novostDTO){
        Novost novost;
        try {
            novost = novostService.create(novostMapper.toEntity(novostDTO));
            novostService.obavestenjeNaEmail(novost);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(novostMapper.toDto(novost), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NovostDTO> updateNovost(
            @RequestBody NovostDTO novostDTO, @PathVariable Integer id){
        Novost novost;
        try {
            novost = novostService.update(novostMapper.toEntity(novostDTO), id);
            novostService.obavestenjeNaEmail(novost);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(novostMapper.toDto(novost), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteNovost(@PathVariable Integer id){
        try {
            novostService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
