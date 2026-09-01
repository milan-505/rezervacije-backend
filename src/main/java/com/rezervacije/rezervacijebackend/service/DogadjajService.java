/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.service;

import com.rezervacije.rezervacijebackend.domain.DogadjajDTO;
import com.rezervacije.rezervacijebackend.jparepo.DogadjajRepository;
import com.rezervacije.rezervacijebackend.jparepo.MestoOdrzavanjaRepository;
import com.rezervacije.rezervacijebackend.mapper.DogadjajMapper;
import com.rezervacije.rezervacijebackend.model.Dogadjaj;
import com.rezervacije.rezervacijebackend.model.MestoOdrzavanja;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */
@Service
public class DogadjajService {

    private final DogadjajRepository repository;
    private final DogadjajMapper mapper;
    private final MestoOdrzavanjaRepository mestoOdrzavanjaRepository;

    public DogadjajService(DogadjajRepository repository, DogadjajMapper mapper,
                            MestoOdrzavanjaRepository mestoOdrzavanjaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.mestoOdrzavanjaRepository = mestoOdrzavanjaRepository;
    }

    public List<DogadjajDTO> search(String naziv) {
        return repository.findByNazivContainingIgnoreCase(naziv).stream()
                .map(mapper::toDogadjajDTO).collect(Collectors.toList());
    }

    public String create(DogadjajDTO dto) {
        try {
            if (dto.getNaziv() == null || dto.getNaziv().isBlank()) {
                return "Naziv dogadjaja je obavezan.";
            }
            if (dto.getDatum() == null || dto.getDatum().isBefore(LocalDate.now())) {
                return "Datum dogadjaja ne moze biti u proslosti.";
            }
            MestoOdrzavanja mesto = mestoOdrzavanjaRepository.findById(dto.getMestoOdrzavanja().getIdMestoOdrzavanja())
                    .orElseThrow(() -> new RuntimeException("Mesto odrzavanja nije pronadjeno."));
            repository.save(mapper.toDogadjajEntity(dto, mesto));
            return "Dogadjaj je uspesno kreiran!";
        } catch (Exception ex) {
            return "Greska prilikom kreiranja!";
        }
    }

    public String update(Long id, DogadjajDTO dto) {
        try {
            if (dto.getNaziv() == null || dto.getNaziv().isBlank()) {
                return "Naziv dogadjaja je obavezan.";
            }
            if (dto.getDatum() == null || dto.getDatum().isBefore(LocalDate.now())) {
                return "Datum dogadjaja ne moze biti u proslosti.";
            }
            Dogadjaj entity = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Dogadjaj nije pronadjen."));
            entity.setNaziv(dto.getNaziv());
            entity.setDatum(dto.getDatum());
            entity.setOpis(dto.getOpis());
            repository.save(entity);
            return "Dogadjaj je uspesno izmenjen!";
        } catch (Exception ex) {
            return "Greska prilikom izmene!";
        }
    }

    public String delete(Long id) {
        try {
            repository.deleteById(id);
            return "Dogadjaj je uspesno obrisan!";
        } catch (Exception ex) {
            return "Greska prilikom brisanja!";
        }
    }
}
