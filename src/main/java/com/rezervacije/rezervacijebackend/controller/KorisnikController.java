/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.controller;

import com.rezervacije.rezervacijebackend.connection.HttpResponse;
import com.rezervacije.rezervacijebackend.connection.Response;
import com.rezervacije.rezervacijebackend.domain.KorisnikDTO;
import com.rezervacije.rezervacijebackend.model.Uloga;
import com.rezervacije.rezervacijebackend.service.KorisnikService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/korisnici")
public class KorisnikController {
    private final KorisnikService service;

    public KorisnikController(KorisnikService service) {
        this.service = service;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody KorisnikDTO dto) {
        dto.setUloga(Uloga.GOST);
        String result = service.create(dto);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }
    
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody KorisnikDTO dto, HttpServletRequest request) {
        KorisnikDTO korisnik = service.login(dto.getUsername(), dto.getPassword());
        if (korisnik == null) {
            return ResponseEntity.ok(HttpResponse.getResponseWithData(
                    "Pogresno korisnicko ime ili lozinka", Map.of(), HttpStatus.UNAUTHORIZED));
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("idKorisnik", korisnik.getIdKorisnik());
        session.setAttribute("uloga", korisnik.getUloga());
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Uspesna prijava", Map.of("value", korisnik), HttpStatus.OK));
    }

    @GetMapping("/session")
    public ResponseEntity<Response> session(HttpSession session) {
        Long idKorisnik = (Long) session.getAttribute("idKorisnik");
        KorisnikDTO korisnik = service.getById(idKorisnik);
        if (korisnik == null) {
            return ResponseEntity.ok(HttpResponse.getResponse("Sesija nije pronadjena.", HttpStatus.UNAUTHORIZED));
        }
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Trenutna sesija", Map.of("value", korisnik), HttpStatus.OK));
    }
    
    @GetMapping("/all")
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Svi korisnici!", Map.of("values", service.getAll()), HttpStatus.OK));
    }
}
