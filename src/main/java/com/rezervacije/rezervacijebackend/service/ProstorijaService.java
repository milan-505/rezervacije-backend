/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.service;

import com.rezervacije.rezervacijebackend.domain.ProstorijaDTO;
import com.rezervacije.rezervacijebackend.jparepo.MestoOdrzavanjaRepository;
import com.rezervacije.rezervacijebackend.jparepo.ProstorijaRepository;
import com.rezervacije.rezervacijebackend.mapper.ProstorijaMapper;
import com.rezervacije.rezervacijebackend.model.MestoOdrzavanja;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */
@Service
public class ProstorijaService {

    private final ProstorijaRepository repository;
    private final ProstorijaMapper mapper;
    private final MestoOdrzavanjaRepository mestoOdrzavanjaRepository;

    public ProstorijaService(ProstorijaRepository repository, ProstorijaMapper mapper,
                              MestoOdrzavanjaRepository mestoOdrzavanjaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.mestoOdrzavanjaRepository = mestoOdrzavanjaRepository;
    }

    public List<ProstorijaDTO> getByMestoOdrzavanja(Long mestoId) {
        MestoOdrzavanja mesto = mestoOdrzavanjaRepository.findById(mestoId).orElse(null);
        if (mesto == null) return List.of();
        return repository.findByMestoOdrzavanja(mesto).stream()
                .map(mapper::toProstorijaDTO).collect(Collectors.toList());
    }

    public String create(ProstorijaDTO dto) {
        try {
            MestoOdrzavanja mesto = mestoOdrzavanjaRepository.findById(dto.getMestoOdrzavanja().getIdMestoOdrzavanja())
                    .orElseThrow(() -> new RuntimeException("Mesto odrzavanja nije pronadjeno."));
            repository.save(mapper.toProstorijaEntity(dto, mesto));
            return "Prostorija je uspesno kreirana!";
        } catch (Exception ex) {
            return "Greska prilikom kreiranja!";
        }
    }
}
