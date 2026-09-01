/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.mapper;

import com.rezervacije.rezervacijebackend.domain.ProstorijaDTO;
import com.rezervacije.rezervacijebackend.model.MestoOdrzavanja;
import com.rezervacije.rezervacijebackend.model.Prostorija;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milan
 */
@Component
public class ProstorijaMapper {
    
    private final MestoOdrzavanjaMapper mestoOdrzavanjaMapper;

    public ProstorijaMapper(MestoOdrzavanjaMapper mestoOdrzavanjaMapper) {
        this.mestoOdrzavanjaMapper = mestoOdrzavanjaMapper;
    }
    
    public ProstorijaDTO toProstorijaDTO(Prostorija prostorija){
        if(prostorija == null) return null;
        ProstorijaDTO prostorijaDTO = new ProstorijaDTO();
        prostorijaDTO.setIdProstorija(prostorija.getIdProstorija());
        prostorijaDTO.setNaziv(prostorija.getNaziv());
        
        if(prostorija.getMestoOdrzavanja()!=null){
            prostorijaDTO.setMestoOdrzavanja(mestoOdrzavanjaMapper.toDomainDTO(prostorija.getMestoOdrzavanja()));
        }
        
        return prostorijaDTO;
    }

    public Prostorija toProstorijaEntity(ProstorijaDTO prostorijaDTO, MestoOdrzavanja mestoOdrzavanja){
        if(prostorijaDTO == null)return null;
        
        Prostorija prostorija = new Prostorija();
        if(prostorijaDTO.getIdProstorija() != null)
            prostorija.setIdProstorija(prostorijaDTO.getIdProstorija());
        prostorija.setNaziv(prostorijaDTO.getNaziv());
        prostorija.setIdMestoOdrzavanja(mestoOdrzavanja);
        
        return prostorija;
    }

   
    
}
