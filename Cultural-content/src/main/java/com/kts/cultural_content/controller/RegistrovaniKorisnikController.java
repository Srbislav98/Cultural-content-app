package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.service.RegistrovaniKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<List<RegistrovaniKorisnikDTO>> getRegistrovaniKorisnici() {

        List<RegistrovaniKorisnik> korisnici = rkService.findAll();

        List<RegistrovaniKorisnikDTO> korisniciDTO = new ArrayList<>();
        for (RegistrovaniKorisnik rk : korisnici) {
            RegistrovaniKorisnikDTO k = new RegistrovaniKorisnikDTO(rk);
            korisniciDTO.add(k);

        }
        return new ResponseEntity<List<RegistrovaniKorisnikDTO>>(korisniciDTO, HttpStatus.OK);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<RegistrovaniKorisnikDTO> getRegistrovanKorisnik(@PathVariable Integer id) {
        RegistrovaniKorisnik rk= rkService.findOne(id);
        if (rk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RegistrovaniKorisnikDTO k = new RegistrovaniKorisnikDTO(rk);
        return new ResponseEntity<RegistrovaniKorisnikDTO>(k, HttpStatus.OK);

    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRegistrovaniKorisnik(@PathVariable Integer id) throws Exception {
        RegistrovaniKorisnik rk= rkService.findOne(id);
        if (rk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        rkService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping(value="/izmeni",consumes = "application/json")
    public ResponseEntity<RegistrovaniKorisnikDTO> updateRegistrovaniKorisnik(@RequestBody RegistrovaniKorisnikDTO rk) {
        RegistrovaniKorisnik k=rkService.findOne(rk.getId());
        if (k == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        k.setIme(rk.getIme());
        k.setPrezime(rk.getPrezime());
        k.setLozinka(rk.getLozinka());
        k=rkService.save(k);
        return new ResponseEntity<RegistrovaniKorisnikDTO>(new RegistrovaniKorisnikDTO(k), HttpStatus.OK);

    }
    @PostMapping(value="/dodaj",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrovaniKorisnikDTO> login(@RequestBody RegistrovaniKorisnikDTO rk){
        rkService.addRegistrovanKorisnik(rk);
        return new ResponseEntity<RegistrovaniKorisnikDTO>(rk, HttpStatus.OK);
    }



}
