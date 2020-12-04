package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.NovostDTO;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.mapper.AdminMapper;
import com.kts.cultural_content.mapper.RegistrovaniKorisnikMapper;
import com.kts.cultural_content.model.Novost;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.service.RegistrovaniKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/registrovaniKorisnici", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrovaniKorisnikController {

    @Autowired
    private RegistrovaniKorisnikService  rkService;
    private RegistrovaniKorisnikMapper rkMapper;
    public RegistrovaniKorisnikController() {
        rkMapper = new RegistrovaniKorisnikMapper();
    }

    @GetMapping
    public ResponseEntity<List<RegistrovaniKorisnikDTO>> getRegistrovaniKorisnici() {

        List<RegistrovaniKorisnik> korisnici = rkService.findAll();

        List<RegistrovaniKorisnikDTO> korisniciDTO = new ArrayList<>();
        for (RegistrovaniKorisnik rk : korisnici) {
            RegistrovaniKorisnikDTO k = rkMapper.toDto(rk);
            korisniciDTO.add(k);

        }
        return new ResponseEntity<List<RegistrovaniKorisnikDTO>>(korisniciDTO, HttpStatus.OK);
    }

    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<RegistrovaniKorisnikDTO>> getAllRegistrovaniKorisnici(Pageable pageable) {
        Page<RegistrovaniKorisnik> page = rkService.findAll(pageable);
        List<RegistrovaniKorisnikDTO> rkDTOS = toRegistrovaniKorisnikDTOList(page.toList());
        Page<RegistrovaniKorisnikDTO> pageRKDTOS = new PageImpl<>(rkDTOS,page.getPageable(),page.getTotalElements());

        return new ResponseEntity<>(pageRKDTOS, HttpStatus.OK);
    }


    @GetMapping(value="/{id}")
    public ResponseEntity<RegistrovaniKorisnikDTO> getRegistrovanKorisnik(@PathVariable Integer id) {
        RegistrovaniKorisnik rk= rkService.findOne(id);
        if (rk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RegistrovaniKorisnikDTO k = rkMapper.toDto(rk);
        return new ResponseEntity<RegistrovaniKorisnikDTO>(k, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrovaniKorisnikDTO> createRegistrovaniKorisnik(@RequestBody RegistrovaniKorisnikDTO rkDTO){
        RegistrovaniKorisnik registrovaniKorisnik;
        try {
            registrovaniKorisnik = rkService.create(rkMapper.toEntity(rkDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(rkMapper.toDto(registrovaniKorisnik), HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrovaniKorisnikDTO> updateRegistrovaniKorisnik(
            @RequestBody RegistrovaniKorisnikDTO rkDTO, @PathVariable Integer id){
        RegistrovaniKorisnik registrovaniKorisnik;
        try {
            registrovaniKorisnik = rkService.update(rkMapper.toEntity(rkDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(rkMapper.toDto(registrovaniKorisnik), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRegistrovaniKorisnik(@PathVariable Integer id){
        try {
            rkService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<RegistrovaniKorisnikDTO> toRegistrovaniKorisnikDTOList(List<RegistrovaniKorisnik> toList) {
        List<RegistrovaniKorisnikDTO> rkDTOS = new ArrayList<>();
        for (RegistrovaniKorisnik registrovaniKorisnik: toList) {
            rkDTOS.add(rkMapper.toDto(registrovaniKorisnik));
        }
        return rkDTOS;
    }


}
