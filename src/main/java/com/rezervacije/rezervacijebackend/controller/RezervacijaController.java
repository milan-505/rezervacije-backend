/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.controller;

import com.rezervacije.rezervacijebackend.connection.HttpResponse;
import com.rezervacije.rezervacijebackend.connection.Response;
import com.rezervacije.rezervacijebackend.domain.RezervacijaDTO;
import com.rezervacije.rezervacijebackend.model.StatusRezervacije;
import com.rezervacije.rezervacijebackend.model.Uloga;
import com.rezervacije.rezervacijebackend.service.RezervacijaService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Milan
 */
@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {

    private final RezervacijaService service;

    public RezervacijaController(RezervacijaService service) {
        this.service = service;
    }

    // Zasticeno AuthInterceptor-om - korisnik mora biti prijavljen.
    // Vlasnik rezervacije se uzima iz sesije, ne iz tela zahteva.
    @PostMapping("/add")
    public ResponseEntity<Response> create(@RequestBody RezervacijaDTO dto, HttpSession session) {
        Long korisnikId = (Long) session.getAttribute("idKorisnik");
        String result = service.create(dto, korisnikId);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }

    // Zasticeno AuthInterceptor-om - gost moze da otkaze samo svoju rezervaciju.
    @PostMapping("/{id}/otkazi")
    public ResponseEntity<Response> cancel(@PathVariable Long id, HttpSession session) {
        Long korisnikId = (Long) session.getAttribute("idKorisnik");
        String result = service.cancel(id, korisnikId);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }

    // Zasticeno AdminInterceptor-om.
    @GetMapping("/pending")
    public ResponseEntity<Response> getPending(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Rezervacije na cekanju!", HttpResponse.pageData(service.getPending(page, size)), HttpStatus.OK));
    }

    // Zasticeno AdminInterceptor-om.
    @GetMapping("/all")
    public ResponseEntity<Response> getAll(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Sve rezervacije!", HttpResponse.pageData(service.getAll(page, size)), HttpStatus.OK));
    }

    // Zasticeno AdminInterceptor-om. status je opcioni filter (npr. ?status=POTVRDJENA);
    // izostavljen = exportuju se sve rezervacije.
    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPdf(@RequestParam(required = false) StatusRezervacije status) {
        byte[] pdf = service.exportPdf(status);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rezervacije.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    // Zasticeno AdminInterceptor-om - adminId se uzima iz sesije, ne iz URL-a.
    @PostMapping("/{id}/potvrdi")
    public ResponseEntity<Response> confirm(@PathVariable Long id, HttpSession session) {
        Long adminId = (Long) session.getAttribute("idKorisnik");
        String result = service.odluciORezervaciji(id, adminId, true);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }

    // Zasticeno AdminInterceptor-om - adminId se uzima iz sesije, ne iz URL-a.
    @PostMapping("/{id}/odbij")
    public ResponseEntity<Response> reject(@PathVariable Long id, HttpSession session) {
        Long adminId = (Long) session.getAttribute("idKorisnik");
        String result = service.odluciORezervaciji(id, adminId, false);
        return ResponseEntity.ok(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
    }

    // Zasticeno AuthInterceptor-om - gost sme da vidi samo svoje rezervacije,
    // administrator sme da vidi bilo cije.
    @GetMapping("/korisnik/{korisnikId}")
    public ResponseEntity<Response> getByKorisnik(@PathVariable Long korisnikId, HttpSession session) {
        Long sessionKorisnikId = (Long) session.getAttribute("idKorisnik");
        Uloga uloga = (Uloga) session.getAttribute("uloga");
        if (uloga != Uloga.ADMINISTRATOR && !korisnikId.equals(sessionKorisnikId)) {
            return ResponseEntity.ok(HttpResponse.getResponse("Nemate pravo da vidite tudje rezervacije.", HttpStatus.FORBIDDEN));
        }
        return ResponseEntity.ok(HttpResponse.getResponseWithData(
                "Rezervacije korisnika!", Map.of("values", service.getByKorisnik(korisnikId)), HttpStatus.OK));
    }
}
