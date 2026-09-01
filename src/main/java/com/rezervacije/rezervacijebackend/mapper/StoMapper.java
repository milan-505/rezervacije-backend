/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.mapper;

import com.rezervacije.rezervacijebackend.domain.StoDTO;
import com.rezervacije.rezervacijebackend.model.Prostorija;
import com.rezervacije.rezervacijebackend.model.Sto;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milan
 */
@Component
public class StoMapper {
    private final ProstorijaMapper prostorijaMapper;

    public StoMapper(ProstorijaMapper prostorijaMapper) {
        this.prostorijaMapper = prostorijaMapper;
    }
    
    public StoDTO toStoDTO(Sto sto){
        if(sto==null)return null;
        StoDTO stoDTO = new StoDTO();
        stoDTO.setIdSto(sto.getIdSto());
        stoDTO.setKapacitet(sto.getKapacitet());
        stoDTO.setOznaka(sto.getOznaka());
        if(sto.getProstorija()!=null)
        stoDTO.setProstorija(prostorijaMapper.toProstorijaDTO(sto.getProstorija()));
        
        return stoDTO;
    }
    
    public Sto toStoEntity(StoDTO stoDTO, Prostorija prostorija){
        if(stoDTO == null)return null;
        Sto sto = new Sto();
        if(stoDTO.getIdSto()!=null)
            sto.setIdSto(stoDTO.getIdSto());
        sto.setKapacitet(stoDTO.getKapacitet());
        sto.setOznaka(stoDTO.getOznaka());
        sto.setProstorija(prostorija);
        
        return sto;
    }
}
