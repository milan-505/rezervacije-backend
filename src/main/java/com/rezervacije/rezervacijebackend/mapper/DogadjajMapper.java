/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.mapper;

import com.rezervacije.rezervacijebackend.domain.DogadjajDTO;
import com.rezervacije.rezervacijebackend.model.Dogadjaj;
import com.rezervacije.rezervacijebackend.model.MestoOdrzavanja;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milan
 */
@Component
public class DogadjajMapper {
    private final MestoOdrzavanjaMapper mestoOdrzavanjaMapper;

    public DogadjajMapper(MestoOdrzavanjaMapper mestoOdrzavanjaMapper) {
        this.mestoOdrzavanjaMapper = mestoOdrzavanjaMapper;
    }
    
    public DogadjajDTO toDogadjajDTO(Dogadjaj dogadjaj){
        if(dogadjaj == null) return null;
        DogadjajDTO dogadjajDTO = new DogadjajDTO();
        dogadjajDTO.setIdDogadjaj(dogadjaj.getIdDogadjaj());
        dogadjajDTO.setNaziv(dogadjaj.getNaziv());
        dogadjajDTO.setOpis(dogadjaj.getOpis());
        dogadjajDTO.setDatum(dogadjaj.getDatum());
        if(dogadjaj.getMestoOdrzavanja()!=null)
        dogadjajDTO.setMestoOdrzavanja(mestoOdrzavanjaMapper.toDomainDTO(dogadjaj.getMestoOdrzavanja()));
        
        return dogadjajDTO;
    }
    
    public Dogadjaj toDogadjajEntity(DogadjajDTO dogadjajDTO, MestoOdrzavanja mestoOdrzavanja){
        if(dogadjajDTO==null)return null;
        
        Dogadjaj dogadjaj = new Dogadjaj();
        if(dogadjajDTO.getIdDogadjaj()!=null)
            dogadjaj.setIdDogadjaj(dogadjajDTO.getIdDogadjaj());
        dogadjaj.setNaziv(dogadjajDTO.getNaziv());
        dogadjaj.setOpis(dogadjajDTO.getOpis());
        dogadjaj.setDatum(dogadjajDTO.getDatum());
        dogadjaj.setMestoOdrzavanja(mestoOdrzavanja);
        
        return dogadjaj;
    }
}
