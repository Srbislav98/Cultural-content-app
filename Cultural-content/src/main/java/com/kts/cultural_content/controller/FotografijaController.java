package com.kts.cultural_content.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.kts.cultural_content.dto.FotografijaDTO;
import com.kts.cultural_content.mapper.FotografijaMapper;
import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.service.FotografijaService;
import com.kts.cultural_content.service.KulturnaPonudaService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/fotografije", produces = MediaType.APPLICATION_JSON_VALUE)
public class FotografijaController {

    @Autowired
    private FotografijaService fotografijaService;
    @Autowired
    private KulturnaPonudaService kulturnaPonudaService;
    private FotografijaMapper fotografijaMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FotografijaDTO>> getAllFotografija(){
        List<Fotografija> fotografijas = fotografijaService.findAll();

        return new ResponseEntity<>(toFotografijaDTOList(fotografijas), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<FotografijaDTO> getFotografija(@PathVariable Integer id){
        Fotografija fotografija = fotografijaService.findOne(id);
        if (fotografija == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fotografijaMapper.toDto(fotografija), HttpStatus.OK);
    }
    @RequestMapping(value = "/getByCulturalId/{id}", method = RequestMethod.GET)
    public ResponseEntity<FotografijaDTO> getFotografijaByCultural(@PathVariable Integer id){
        KulturnaPonuda kulturnaPonuda = kulturnaPonudaService.findOne(id);
        Fotografija temp = new Fotografija();
        for (Fotografija f:kulturnaPonuda.getFotogrfija()) {
            temp = f;
            break;
        }
        FotografijaDTO f=fotografijaMapper.toDto(temp);
        f.setLokacijaFajl(temp.getLokacijaFajl());
        return new ResponseEntity<>(f, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    //@RequestMapping(value = "/create",method=RequestMethod.POST,)
    @PostMapping("/create/cult-id/{id}")
    public ResponseEntity createFotografija(@RequestParam("File") MultipartFile fajl,@PathVariable Integer id) throws IOException {
        /*
        System.out.println("sssssss");
        System.out.println(fajl.getOriginalFilename());
         */
        Fotografija fotografija=new Fotografija();

        String s=System.getProperty("file.separator");
        File f=new File(".");
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getCanonicalPath().split("Cultural-content")[0]);
        System.out.println(f.getPath());
        byte[] bytes = fajl.getBytes();
        try (FileOutputStream fos = new FileOutputStream((f.getCanonicalPath().split("Cultural-content")[0]+"frontend/src/assets/img/"+fajl.getOriginalFilename()+id)))
        {
            fos.write(bytes);
        }
       // File fajlproba=new File("jako");
       // file.getParentFile().getName();
        File fajlic = new File(f.getCanonicalPath().split("Cultural-content")[0]+"frontend/src/assets/img/"+fajl.getOriginalFilename());
        fotografija.setFoto(fajlic);
        fotografija.setId(1);
        fotografija.setKulId(id);
        fotografija.setRecId(0);
        fotografija.setLokacijaFajl(f.getCanonicalPath().split("Cultural-content")[0]+"frontend/src/assets/img/"+fajl.getOriginalFilename());
        try {
            if( ImageIO.read(new File(fotografija.getLokacijaFajl())) == null)
                throw new IOException("Ovo nije slika");
            fotografija = fotografijaService.create(fotografija);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/createForRec/kulturna/{id}/registrovani/{id2}")
    public ResponseEntity createFotografijaRec(@RequestParam("File") MultipartFile fajl,@PathVariable Integer id, @PathVariable Integer id2) throws IOException {

        File f = new File(".");
        File file = new File(f.getCanonicalPath().split("Cultural-content")[0]+"frontend/src/assets/img/"+fajl.getOriginalFilename());

        fajl.transferTo(file);

        fotografijaService.createForRec(file,id,id2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotografijaDTO> updateFotografija(@RequestBody FotografijaDTO fotografijaDTO, @PathVariable Integer id){
        Fotografija fotografija;
        try {
            if (fotografijaDTO.getFoto()==null)
                if( ImageIO.read(new File(fotografijaDTO.getLokacijaFajl())) == null)
                    throw new IOException("Ovo nije slika");
            fotografija = fotografijaService.update(fotografijaMapper.toEntity(fotografijaDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(fotografijaMapper.toDto(fotografija), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFotografija(@PathVariable Integer id){
        System.out.println("Ovde");
        System.out.println(id);
        try {
            fotografijaService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Ovde");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public FotografijaController() {
        fotografijaMapper = new FotografijaMapper();
    }

    private List<FotografijaDTO> toFotografijaDTOList(List<Fotografija> fotografijas){
        List<FotografijaDTO> fotografijaDTOS = new ArrayList<>();
        for (Fotografija fotografija : fotografijas){
            fotografijaDTOS.add(fotografijaMapper.toDto(fotografija));
        }
        return fotografijaDTOS;
    }

}
