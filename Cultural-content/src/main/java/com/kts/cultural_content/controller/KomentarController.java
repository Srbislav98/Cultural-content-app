package com.kts.cultural_content.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/komentari", produces = MediaType.APPLICATION_JSON_VALUE)
public class KomentarController {
}