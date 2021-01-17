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
import java.util.Set;

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

    @RequestMapping(value = "/filter-by-location/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<KulturnaPonudaDTO>> getAllKulturnePonudebyLocation(@PathVariable String name) {
        List<KulturnaPonuda> kulturnePonude = kulturnaPonudaService.filterByLocation(name);

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
            System.out.println("####################################################");
            System.out.println("DTO : ");
            System.out.println("\tID - " + kulturnaPonudaDTO.getId());
            System.out.println("\tNaziv - " + kulturnaPonudaDTO.getNaziv());
            System.out.println("\tID Tipa KP - " + kulturnaPonudaDTO.getIdt());
            System.out.println("####################################################");

            KulturnaPonuda map = kulturnaPonudaMapper.toEntity(kulturnaPonudaDTO);

            System.out.println("####################################################");
            System.out.println("KP pre kulturnaPonudaService : ");
            System.out.println("\tID - " + map.getId());
            System.out.println("\tNaziv - " + map.getNaziv());
            System.out.println("\tTip KP - " + map.getTipKulturnePonude().getNaziv() + "; ID - " + map.getTipKulturnePonude().getId());
            System.out.println("\tAdmin - " + map.getAdmin().getEmail() + "; ID - " + map.getAdmin().getId());
            System.out.println("####################################################");

            kulturnaPonuda = kulturnaPonudaService.create(map);

            System.out.println("####################################################");
            System.out.println("KP posle kulturnaPonudaService : ");
            System.out.println("\tID - " + kulturnaPonuda.getId());
            System.out.println("\tNaziv - " + kulturnaPonuda.getNaziv());
            System.out.println("\tTip KP - " + kulturnaPonuda.getTipKulturnePonude().getNaziv() + "; ID - " + kulturnaPonuda.getTipKulturnePonude().getId());
            System.out.println("\tAdmin - " + kulturnaPonuda.getAdmin().getEmail() + "; ID - " + kulturnaPonuda.getAdmin().getId());
            System.out.println("####################################################");
        } catch (Exception e) {
            System.out.println("ResponseEntity<KulturnaPonudaDTO> createKulturnaPonuda");
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/getNovosti/{id}", method = RequestMethod.GET)
    public ResponseEntity< List<Novost>> getKulturnaPonudaNovosti(@PathVariable Integer id){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ArrayList<Novost> lista = new ArrayList<Novost>();
        for (Novost novost : kulturnaPonuda.getNovosti())
            lista.add(new Novost(novost.getNaziv(),novost.getOpis(),novost.getDatum()));
        return new ResponseEntity<List<Novost>>(lista, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/getProsecnaOcena/{id}", method = RequestMethod.GET)
    public ResponseEntity<Float> getProsecnaOcena(@PathVariable Integer id){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Float d = kulturnaPonuda.prosecnaOcena();
        return new ResponseEntity<Float>(d, HttpStatus.OK);
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
