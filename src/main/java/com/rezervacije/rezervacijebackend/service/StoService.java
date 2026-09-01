/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.service;

import com.rezervacije.rezervacijebackend.domain.StoDTO;
import com.rezervacije.rezervacijebackend.jparepo.ProstorijaRepository;
import com.rezervacije.rezervacijebackend.jparepo.StoRepository;
import com.rezervacije.rezervacijebackend.mapper.StoMapper;
import com.rezervacije.rezervacijebackend.model.Prostorija;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */
@Service
public class StoService {

    private final StoRepository repository;
    private final StoMapper mapper;
    private final ProstorijaRepository prostorijaRepository;

    public StoService(StoRepository repository, StoMapper mapper, ProstorijaRepository prostorijaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.prostorijaRepository = prostorijaRepository;
    }

    public List<StoDTO> getByProstorija(Long prostorijaId) {
        Prostorija prostorija = prostorijaRepository.findById(prostorijaId).orElse(null);
        if (prostorija == null) return List.of();
        return repository.findByProstorija(prostorija).stream()
                .map(mapper::toStoDTO).collect(Collectors.toList());
    }

    public String create(StoDTO dto) {
        try {
            Prostorija prostorija = prostorijaRepository.findById(dto.getProstorija().getIdProstorija())
                    .orElseThrow(() -> new RuntimeException("Prostorija nije pronadjena."));
            repository.save(mapper.toStoEntity(dto, prostorija));
            return "Sto je uspesno kreiran!";
        } catch (Exception ex) {
            return "Greska prilikom kreiranja!";
        }
    }
}
