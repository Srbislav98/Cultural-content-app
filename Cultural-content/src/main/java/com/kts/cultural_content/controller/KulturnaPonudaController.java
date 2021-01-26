package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.dto.NovostDTO;
import com.kts.cultural_content.dto.RecenzijaDTO;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.mapper.KulturnaPonudaMapper;
import com.kts.cultural_content.mapper.NovostMapper;
import com.kts.cultural_content.model.*;
import com.kts.cultural_content.service.KulturnaPonudaService;
import com.kts.cultural_content.service.NovostService;
import com.kts.cultural_content.service.RecenzijaService;
import com.kts.cultural_content.service.RegistrovaniKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

@RestController
@RequestMapping(value = "/api/kulturnePonude", produces = MediaType.APPLICATION_JSON_VALUE)
public class KulturnaPonudaController {

    @Autowired
    private KulturnaPonudaService kulturnaPonudaService;
    @Autowired
    private RegistrovaniKorisnikService rkService;
    @Autowired
    private RecenzijaService recenzijaService;

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
    @RequestMapping(value = "/filter-by-location/by-page/{name}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Page<KulturnaPonudaDTO>> getAllKulturnePonudebyNaziv(@PathVariable String name,Pageable pageable) {
        List<KulturnaPonuda> kulturnePonude = kulturnaPonudaService.filterByContent(name);
        List<KulturnaPonudaDTO> kpDTOS=new ArrayList<KulturnaPonudaDTO>();
        int pocetak=pageable.getPageNumber()*pageable.getPageSize();
        int kreni=0,moze= pageable.getPageSize();
        ArrayList<KulturnaPonudaDTO> kponude=new ArrayList<KulturnaPonudaDTO>();
        for(KulturnaPonuda kup:kulturnePonude){
            kponude.add(kulturnaPonudaMapper.toDto(kup));
        }
        Collections.sort(kponude);
        for (KulturnaPonudaDTO kpa: kponude) {
            if(pocetak==kreni && moze>0) {
                kpDTOS.add(kpa);
                moze--;
                pocetak++;
            }
            kreni++;
        }
        Page<KulturnaPonudaDTO> pageRKDTOS = new PageImpl<>(kpDTOS,pageable,kponude.size());
        System.out.println("BBBBBBBBBBBBBBBBBBBBBB");

        return new ResponseEntity<Page<KulturnaPonudaDTO>>(pageRKDTOS, HttpStatus.OK);
    }
    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<KulturnaPonudaDTO>> getAllKulturnaPonuda(Pageable pageable) {
        Page<KulturnaPonuda> page = kulturnaPonudaService.findAll(pageable);
        List<KulturnaPonudaDTO> komentarDTOS = toKulturnaPonudaDTOList(page.toList());
        Page<KulturnaPonudaDTO> pageKomentarDTOS = new PageImpl<>(komentarDTOS,page.getPageable(),page.getTotalElements());

        return new ResponseEntity<>(pageKomentarDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter-by-content-page/{content}", method = RequestMethod.GET)
    public ResponseEntity<Page<KulturnaPonudaDTO>> getAllKulturnePonudebyContentPage(@PathVariable String content, Pageable pageable) {
        Page<KulturnaPonuda> page = kulturnaPonudaService.filterByContentPage(pageable, content);
        List<KulturnaPonudaDTO> filterDTOs = toKulturnaPonudaDTOList(page.toList());
        Page<KulturnaPonudaDTO> pageFilterDTOS = new PageImpl<>(filterDTOs, page.getPageable(), page.getTotalElements());

        return new ResponseEntity<>(pageFilterDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter-by-location-page/{name}", method = RequestMethod.GET)
    public ResponseEntity<Page<KulturnaPonudaDTO>> getAllKulturnePonudebyLocationPage(@PathVariable String name, Pageable pageable) {
        Page<KulturnaPonuda> page = kulturnaPonudaService.filterByLocationPage(pageable, name);
        List<KulturnaPonudaDTO> filterDTOs = toKulturnaPonudaDTOList(page.toList());
        Page<KulturnaPonudaDTO> pageFilterDTOS = new PageImpl<>(filterDTOs, page.getPageable(), page.getTotalElements());

        return new ResponseEntity<>(pageFilterDTOS, HttpStatus.OK);
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
            KulturnaPonuda kp=kulturnaPonudaService.findOne(id);
            for(RegistrovaniKorisnik rk : kp.getRegistrovaniKorisnik()){
                rkService.unsubscribe(rk.getId(), id);
            }
            kulturnaPonudaService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/getNovosti/{id}", method = RequestMethod.GET)
    public ResponseEntity< Page<NovostDTO>> getKulturnaPonudaNovosti(@PathVariable Integer id, Pageable pageable){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        KulturnaPonudaDTO k = kulturnaPonudaMapper.toDto(kulturnaPonuda);
        //NovostMapper novostMapper = null;
        List<NovostDTO> nDTOS = new ArrayList<>();
        int pocetak = pageable.getPageNumber()*pageable.getPageSize();
        int kreni = 0, moze = pageable.getPageSize();
        ArrayList<NovostDTO> nPonude = new ArrayList<NovostDTO>();
        for( Novost nov: kulturnaPonuda.getNovosti()){
            NovostDTO test = new NovostDTO(nov.getId(),nov.getNaziv(),nov.getOpis(),nov.getDatum());
            nPonude.add(test);
        }
        //Collections.sort(nPonude, null);
        nPonude = bubble_sort(nPonude);
        for(NovostDTO kpa : nPonude){
            if(pocetak==kreni && moze>0){
                nDTOS.add(kpa);
                moze--;
                pocetak++;
            }
            kreni++;
        }
        Page<NovostDTO> novostDTOPage = new PageImpl<>(nDTOS,pageable,kulturnaPonuda.getNovosti().size());
        return new ResponseEntity<Page<NovostDTO>>(novostDTOPage, HttpStatus.OK);
    }

    public ArrayList<NovostDTO> bubble_sort(ArrayList<NovostDTO> lista){
        int n = lista.size();
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (lista.get(i).getDatum().compareTo(lista.get(k).getDatum())<0) {
                    NovostDTO temp = lista.get(i);
                    lista.set(i, lista.get(k));
                    lista.set(k , temp);
                }
            }
            //printNumbers(array);
        }
        return lista;
    }


    @RequestMapping(value = "/getProsecnaOcena/{id}", method = RequestMethod.GET)
    public ResponseEntity<Float> getProsecnaOcena(@PathVariable Integer id){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Float d = kulturnaPonuda.prosecnaOcena();
        return new ResponseEntity<Float>(d, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/daLiSadrzi/{id}/registrovani/{id2}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getDaLiJe(@PathVariable Integer id, @PathVariable Integer id2){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Boolean ima = false;
        for(RegistrovaniKorisnik registrovaniKorisnik:kulturnaPonuda.getRegistrovaniKorisnik()){
            if (registrovaniKorisnik.getId()==id2){
                ima = true;
                break;
            }
        }
        return new ResponseEntity<Boolean>(ima, HttpStatus.OK);
    }

    @RequestMapping(value = "/getRecenzije/{id}", method = RequestMethod.GET)
    public ResponseEntity< Page<RecenzijaDTO>> getKulturnaPonudaRecenzije(@PathVariable Integer id, Pageable pageable){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        KulturnaPonudaDTO k = kulturnaPonudaMapper.toDto(kulturnaPonuda);
        //NovostMapper novostMapper = null;
        List<RecenzijaDTO> nDTOS = new ArrayList<>();
        int pocetak = pageable.getPageNumber()*pageable.getPageSize();
        int kreni = 0, moze = pageable.getPageSize();
        ArrayList<RecenzijaDTO> nPonude = new ArrayList<RecenzijaDTO>();
        for( Recenzija nov: kulturnaPonuda.getRecenzije()){
            RecenzijaDTO test = new RecenzijaDTO(nov.getId(),nov.getOcena(),nov.getKomentar(),nov.getRegId(), nov.getKulId(),nov.getFoto());
            nPonude.add(test);
        }
        //Collections.sort(nPonude, null);
        //nPonude = bubble_sort(nPonude);
        for(RecenzijaDTO kpa : nPonude){
            if(pocetak==kreni && moze>0){
                nDTOS.add(kpa);
                moze--;
                pocetak++;
            }
            kreni++;
        }
        Page<RecenzijaDTO> novostDTOPage = new PageImpl<>(nDTOS,pageable,kulturnaPonuda.getRecenzije().size());
        return new ResponseEntity<Page<RecenzijaDTO>>(novostDTOPage, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter-by-grade/{content}/kulturna/{id}", method = RequestMethod.GET)
    public ResponseEntity< Page<RecenzijaDTO>> getKulturnaPonudaRecenzije(@PathVariable Integer id, @PathVariable Integer content, Pageable pageable){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Recenzija> recenzijas = recenzijaService.filterByGrade(content,id);
        KulturnaPonudaDTO k = kulturnaPonudaMapper.toDto(kulturnaPonuda);
        //NovostMapper novostMapper = null;
        List<RecenzijaDTO> nDTOS = new ArrayList<>();
        int pocetak = pageable.getPageNumber()*pageable.getPageSize();
        int kreni = 0, moze = pageable.getPageSize();
        ArrayList<RecenzijaDTO> nPonude = new ArrayList<RecenzijaDTO>();
        for( Recenzija nov: kulturnaPonuda.getRecenzije()){
            for(Recenzija recenzija:recenzijas) {
                if (nov.getId()==recenzija.getId()) {
                    RecenzijaDTO test = new RecenzijaDTO(nov.getId(), nov.getOcena(), nov.getKomentar(), nov.getRegId(), nov.getKulId(), nov.getFoto());
                    nPonude.add(test);
                }
            }
        }
        //Collections.sort(nPonude, null);
        //nPonude = bubble_sort(nPonude);
        for(RecenzijaDTO kpa : nPonude){
            if(pocetak==kreni && moze>0){
                nDTOS.add(kpa);
                moze--;
                pocetak++;
            }
            kreni++;
        }
        Page<RecenzijaDTO> novostDTOPage = new PageImpl<>(nDTOS,pageable,nPonude.size());
        return new ResponseEntity<Page<RecenzijaDTO>>(novostDTOPage, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/vecDaoReview/{id}/registrovani/{id2}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getVecDaoReview(@PathVariable Integer id, @PathVariable Integer id2){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Boolean ima = false;
        for(Recenzija recenzija:kulturnaPonuda.getRecenzije()){
            if (recenzija.getRegistrovaniKorisnik().getId()==id2){
                ima = true;
                break;
            }
        }
        return new ResponseEntity<Boolean>(ima, HttpStatus.OK);
    }


    @RequestMapping(value = "/getSlikaKul/{id}", method = RequestMethod.GET)
    public ResponseEntity<Fotografija> getSlikaKul(@PathVariable Integer id){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        if (kulturnaPonuda == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Fotografija d = new Fotografija();
        d.setLokacijaFajl("assets/img/R6eb915d96d4c990aaf152a70c5fb54a9.png");
        for(Fotografija fotografija:kulturnaPonuda.getFotogrfija()){
            d.setLokacijaFajl("assets/img/"+fotografija.getFoto().getName());

            break;
        }

        //String d = kulturnaPonuda.getFotogrfija()
        return new ResponseEntity<Fotografija>(d, HttpStatus.OK);
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
