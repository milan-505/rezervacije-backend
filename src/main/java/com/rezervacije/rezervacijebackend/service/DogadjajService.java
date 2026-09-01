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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    /**
     * Pretraga dogadjaja po nazivu (prazan string = svi dogadjaji), sortirano
     * po datumu - najbliza sledeca dogadjanja prva.
     */
    public Page<DogadjajDTO> search(String naziv, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("datum").ascending());
        String pojam = naziv != null ? naziv : "";
        return repository.findByNazivContainingIgnoreCase(pojam, pageable).map(mapper::toDogadjajDTO);
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
