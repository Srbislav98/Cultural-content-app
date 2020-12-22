package com.kts.cultural_content.controller;

import com.kts.cultural_content.dto.AdminDTO;
import com.kts.cultural_content.dto.RegistrovaniKorisnikDTO;
import com.kts.cultural_content.mapper.AdminMapper;
import com.kts.cultural_content.model.Admin;
import com.kts.cultural_content.model.RegistrovaniKorisnik;
import com.kts.cultural_content.service.AdminService;
import com.kts.cultural_content.service.RegistrovaniKorisnikService;
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
@RequestMapping(value = "/api/admins", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private AdminService adminService;
    private AdminMapper adminMapper;


    public AdminController() {
        adminMapper = new AdminMapper();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<Admin> admins= adminService.findAll();
        if (admins.size()==0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<AdminDTO> adminsDTO = new ArrayList<>();
        for(Admin rk : admins) {
            AdminDTO k = adminMapper.toDto(rk);
            adminsDTO.add(k);
        }
        //AdminDTO k = adminMapper.toDto(admins.get(0));
        List<AdminDTO>admini=adminsDTO;
        return new ResponseEntity<List<AdminDTO>>(admini, HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<AdminDTO>> getAllAdmins(Pageable pageable) {
        Page<Admin> page = adminService.findAll(pageable);
        List<AdminDTO> adminDTOS = toAdminDTOList(page.toList());
        Page<AdminDTO> pageAdminDTOS = new PageImpl<>(adminDTOS,page.getPageable(),page.getTotalElements());

        return new ResponseEntity<>(pageAdminDTOS, HttpStatus.OK);
    }

    private List<AdminDTO> toAdminDTOList(List<Admin> toList) {
        List<AdminDTO> adminDTOS = new ArrayList<>();
        for (Admin a: toList) {
            adminDTOS.add(adminMapper.toDto(a));
        }
        return adminDTOS;
    }

}
