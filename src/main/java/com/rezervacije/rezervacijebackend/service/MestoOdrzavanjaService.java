/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.service;

import com.rezervacije.rezervacijebackend.domain.MestoOdrzavanjaDTO;
import com.rezervacije.rezervacijebackend.jparepo.MestoOdrzavanjaRepository;
import com.rezervacije.rezervacijebackend.mapper.MestoOdrzavanjaMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */
@Service
public class MestoOdrzavanjaService {

    private final MestoOdrzavanjaRepository repository;
    private final MestoOdrzavanjaMapper mapper;

    public MestoOdrzavanjaService(MestoOdrzavanjaRepository repository, MestoOdrzavanjaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MestoOdrzavanjaDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDomainDTO).collect(Collectors.toList());
    }

    public String create(MestoOdrzavanjaDTO dto) {
        try {
            repository.save(mapper.toDomainEntity(dto));
            return "Mesto održavanja je uspešno kreirano!";
        } catch (Exception ex) {
            return "Greška prilikom kreiranja!";
        }
    }
}