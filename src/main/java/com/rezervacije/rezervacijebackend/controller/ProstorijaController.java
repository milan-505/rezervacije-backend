/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.controller;

import com.rezervacije.rezervacijebackend.connection.HttpResponse;
import com.rezervacije.rezervacijebackend.connection.Response;
import com.rezervacije.rezervacijebackend.domain.ProstorijaDTO;
import com.rezervacije.rezervacijebackend.service.ProstorijaService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Milan
 */
@RestController
@RequestMapping("/prostorije")
public class ProstorijaController {

    private final ProstorijaService service;

    public ProstorijaController(ProstorijaService service) {
        this.service = service;
    }

    @GetMapping("/mesto/{mestoId}")
    public ResponseEntity<Response> getByMesto(@PathVariable Long mestoId) {
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Prostorije za izabrano mesto!", Map.of("values", service.getByMestoOdrzavanja(mestoId)), HttpStatus.OK));
    }

    @PostMapping("/add")
    public ResponseEntity<Response> create(@RequestBody ProstorijaDTO dto) {
        String result = service.create(dto);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }
}
