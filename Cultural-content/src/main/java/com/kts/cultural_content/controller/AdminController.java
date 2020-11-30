package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.AdminDTO;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.service.AdminService;
import com.kts.cultural_content.service.RegistrovaniKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admins", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<AdminDTO> getAdmin() {
        List<Admin> admins= adminService.findAll();
        if (admins == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AdminDTO k = new AdminDTO(admins.get(0));
        return new ResponseEntity<AdminDTO>(k, HttpStatus.OK);

    }

}
