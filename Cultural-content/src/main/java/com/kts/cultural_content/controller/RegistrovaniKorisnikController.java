package com.kts.cultural_content.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.kts.cultural_content.dto.KulturnaPonudaDTO;
import com.kts.cultural_content.dto.NovostDTO;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.dto.UserLoginDTO;
import com.kts.cultural_content.mapper.AdminMapper;
import com.kts.cultural_content.mapper.KulturnaPonudaMapper;
import com.kts.cultural_content.mapper.RegistrovaniKorisnikMapper;
import com.kts.cultural_content.model.Korisnik;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.Novost;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.service.KulturnaPonudaService;
import com.kts.cultural_content.service.RegistrovaniKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/api/registrovaniKorisnici", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrovaniKorisnikController {

    @Autowired
    private RegistrovaniKorisnikService  rkService;
    @Autowired
    private KulturnaPonudaService kulturnaPonudaService;
    private KulturnaPonudaMapper kulturnaPonudaMapper;
    private RegistrovaniKorisnikMapper rkMapper;
    private KulturnaPonudaMapper kpMapper;
    public RegistrovaniKorisnikController() {
        rkMapper = new RegistrovaniKorisnikMapper();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<RegistrovaniKorisnikDTO>> getAllRegistrovaniKorisnici(Pageable pageable) {
        Page<RegistrovaniKorisnik> page = rkService.findAll(pageable);
        System.out.println("XMUSKY:"+page.getTotalElements()+":"+page.stream().count());
        List<RegistrovaniKorisnikDTO> rkDTOS = toRegistrovaniKorisnikDTOList(page.toList());
        System.out.println(rkDTOS.size());
        Page<RegistrovaniKorisnikDTO> pageRKDTOS = new PageImpl<>(rkDTOS,page.getPageable(),page.getTotalElements());

        return new ResponseEntity<>(pageRKDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value="/{id}")
    public ResponseEntity<RegistrovaniKorisnikDTO> getRegistrovanKorisnik(@PathVariable Integer id) {
        RegistrovaniKorisnik rk= rkService.findOne(id);
        if (rk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RegistrovaniKorisnikDTO k = rkMapper.toDto(rk);
        return new ResponseEntity<RegistrovaniKorisnikDTO>(k, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value="/user", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrovaniKorisnikDTO> getRegistrovanKorisnikFull(@RequestBody UserLoginDTO u) {
        RegistrovaniKorisnik rk= rkService.findByEmail(u.getUsername());
        if (rk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RegistrovaniKorisnikDTO k = rkMapper.toDto(rk);
        return new ResponseEntity<RegistrovaniKorisnikDTO>(k, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrovaniKorisnikDTO> createRegistrovaniKorisnik(@Valid @RequestBody RegistrovaniKorisnikDTO rkDTO){
        RegistrovaniKorisnik registrovaniKorisnik;
        rkMapper.toEntity(rkDTO);
        try {
            registrovaniKorisnik = rkService.create(rkMapper.toEntity(rkDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(rkMapper.toDto(registrovaniKorisnik), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrovaniKorisnikDTO> updateRegistrovaniKorisnik(
            @Valid @RequestBody RegistrovaniKorisnikDTO rkDTO, @PathVariable Integer id){
        RegistrovaniKorisnik registrovaniKorisnik;
        System.out.println("20000000000000021");
        System.out.println(rkDTO.getIme()+" "+rkDTO.getLozinka());
        try {
            registrovaniKorisnik = rkService.update(rkMapper.toEntity(rkDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(rkMapper.toDto(registrovaniKorisnik), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRegistrovaniKorisnik(@PathVariable Integer id){
        try {
            rkService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/subscribe/{id}/kulturnaPonuda/{id2}", method = RequestMethod.PUT )
    public ResponseEntity<Void> subscribe(@PathVariable Integer id, @PathVariable Integer id2){
        try {
            rkService.subscribe(id,id2);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/unsubscribe/{id}/kulturnaPonuda/{id2}", method = RequestMethod.DELETE )
    public ResponseEntity<Void> unsubscribe(@PathVariable Integer id, @PathVariable Integer id2){
        try {
            rkService.unsubscribe(id,id2);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value="/allsubscription/{id}")
    public ResponseEntity< Set<KulturnaPonudaDTO>> getRegistrovanKorisnikSubscriptions(@PathVariable Integer id) {
        RegistrovaniKorisnik rk= rkService.findOne(id);
        if (rk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RegistrovaniKorisnikDTO k = rkMapper.toDto(rk);
        return new ResponseEntity<Set<KulturnaPonudaDTO>>( k.getKulturnaPonuda(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public ResponseEntity<Integer> getId(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Korisnik u=(Korisnik)auth.getPrincipal();
        return new ResponseEntity<Integer>(u.getId(),HttpStatus.OK);
    }

    @RequestMapping(value = "/filter-by-location/{name}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Page<KulturnaPonudaDTO>> getAllKulturnePonudebyNaziv(@PathVariable String name,Pageable pageable) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        Korisnik u=(Korisnik)auth.getPrincipal();
        RegistrovaniKorisnik rk= rkService.findByEmail(u.getEmail());
        if (rk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<KulturnaPonuda> kulturnePonude = kulturnaPonudaService.filterByContent(name);
        RegistrovaniKorisnikDTO k = rkMapper.toDto(rk);
        List<KulturnaPonudaDTO> kpDTOS=new ArrayList<KulturnaPonudaDTO>();
        int pocetak=pageable.getPageNumber()*pageable.getPageSize();
        int kreni=0,moze= pageable.getPageSize();
        ArrayList<KulturnaPonudaDTO> kponude=new ArrayList<KulturnaPonudaDTO>();
        for (KulturnaPonudaDTO kpa: k.getKulturnaPonuda()) {
            for(KulturnaPonuda kup:kulturnePonude){
                if(kup.getId()==kpa.getId()){
                    kponude.add(kpa);
                }
            }
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value="/allsubscriptions/{id}")
    public ResponseEntity<Page<KulturnaPonudaDTO>> getRegistrovanKorisnikSubscriptionsPage( @PathVariable Integer id, Pageable pageable) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        /*headers.forEach((key, value) -> {
            System.out.println(key+":"+value);
        });*/
        RegistrovaniKorisnik rk= rkService.findOne(id);
        if (rk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RegistrovaniKorisnikDTO k = rkMapper.toDto(rk);
        //Page<KulturnaPonuda> kpage
        //return new ResponseEntity<Set<KulturnaPonudaDTO>>( k.getKulturnaPonuda(), HttpStatus.OK);

        List<KulturnaPonudaDTO> kpDTOS=new ArrayList<KulturnaPonudaDTO>();
        //pageable.getPageNumber();
        //pageable.getPageSize();
        int pocetak=pageable.getPageNumber()*pageable.getPageSize();
        int kreni=0,moze= pageable.getPageSize();
        ArrayList<KulturnaPonudaDTO> kponude=new ArrayList<KulturnaPonudaDTO>();
        for (KulturnaPonudaDTO kpa: k.getKulturnaPonuda()) {
            kponude.add(kpa);
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
        Page<KulturnaPonudaDTO> pageRKDTOS = new PageImpl<>(kpDTOS,pageable,k.getKulturnaPonuda().size());
        System.out.println("BBBBBBBBBBBBBBBBBBBBBB");

        return new ResponseEntity<>(pageRKDTOS, HttpStatus.OK);
    }

    private List<RegistrovaniKorisnikDTO> toRegistrovaniKorisnikDTOList(List<RegistrovaniKorisnik> toList) {
        List<RegistrovaniKorisnikDTO> rkDTOS = new ArrayList<>();
        for (RegistrovaniKorisnik registrovaniKorisnik: toList) {
            rkDTOS.add(rkMapper.toDto(registrovaniKorisnik));
        }
        return rkDTOS;
    }
    private List<KulturnaPonudaDTO> toKulturnaPonudaDTOList(List<KulturnaPonuda> kulturnaPonude){
        List<KulturnaPonudaDTO> kulturnaPonudaDTOS = new ArrayList<>();
        for (KulturnaPonuda kulturnaPonuda : kulturnaPonude){
            kulturnaPonudaDTOS.add(kulturnaPonudaMapper.toDto(kulturnaPonuda));
        }
        return kulturnaPonudaDTOS;
    }


}
