/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.controller;

import com.rezervacije.rezervacijebackend.connection.HttpResponse;
import com.rezervacije.rezervacijebackend.connection.Response;
import com.rezervacije.rezervacijebackend.domain.StoDTO;
import com.rezervacije.rezervacijebackend.service.StoService;
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
@RequestMapping("/stolovi")
public class StoController {

    private final StoService service;

    public StoController(StoService service) {
        this.service = service;
    }

    @GetMapping("/prostorija/{prostorijaId}")
    public ResponseEntity<Response> getByProstorija(@PathVariable Long prostorijaId) {
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Stolovi za izabranu prostoriju!", Map.of("values", service.getByProstorija(prostorijaId)), HttpStatus.OK));
    }

    @PostMapping("/add")
    public ResponseEntity<Response> create(@RequestBody StoDTO dto) {
        String result = service.create(dto);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }
}
