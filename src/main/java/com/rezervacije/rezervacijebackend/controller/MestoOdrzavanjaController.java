/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.controller;

import com.rezervacije.rezervacijebackend.connection.HttpResponse;
import com.rezervacije.rezervacijebackend.connection.Response;
import com.rezervacije.rezervacijebackend.domain.MestoOdrzavanjaDTO;
import com.rezervacije.rezervacijebackend.service.MestoOdrzavanjaService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Milan
 */
@RestController
@RequestMapping("/mesta-odrzavanja")
public class MestoOdrzavanjaController {

    private final MestoOdrzavanjaService service;

    public MestoOdrzavanjaController(MestoOdrzavanjaService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Response> create(@RequestBody MestoOdrzavanjaDTO dto) {
        String result = service.create(dto);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Sva mesta odrzavanja", Map.of("values", service.getAll()), HttpStatus.OK));
    }
}
