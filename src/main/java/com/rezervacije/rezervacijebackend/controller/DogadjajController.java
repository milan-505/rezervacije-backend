/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.controller;

import com.rezervacije.rezervacijebackend.connection.HttpResponse;
import com.rezervacije.rezervacijebackend.connection.Response;
import com.rezervacije.rezervacijebackend.domain.DogadjajDTO;
import com.rezervacije.rezervacijebackend.service.DogadjajService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Milan
 */
@RestController
@RequestMapping("/dogadjaji")
public class DogadjajController {

    private final DogadjajService service;

    public DogadjajController(DogadjajService service) {
        this.service = service;
    }

    @GetMapping("/pretraga")
    public ResponseEntity<Response> search(@RequestParam String naziv) {
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Pronadjeni dogadjaji!", Map.of("values", service.search(naziv)), HttpStatus.OK));
    }

    @PostMapping("/add")
    public ResponseEntity<Response> create(@RequestBody DogadjajDTO dto) {
        String result = service.create(dto);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody DogadjajDTO dto) {
        String result = service.update(id, dto);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        String result = service.delete(id);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }
}
