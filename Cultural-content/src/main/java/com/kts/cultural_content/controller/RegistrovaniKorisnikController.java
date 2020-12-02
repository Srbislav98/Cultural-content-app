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
}
