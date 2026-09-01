/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.mapper;

import com.rezervacije.rezervacijebackend.domain.MestoOdrzavanjaDTO;
import com.rezervacije.rezervacijebackend.model.MestoOdrzavanja;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milan
 */
@Component
public class MestoOdrzavanjaMapper implements BaseMapper<MestoOdrzavanjaDTO, MestoOdrzavanja>{

    @Override
    public MestoOdrzavanjaDTO toDomainDTO(MestoOdrzavanja domainEntity) {
        MestoOdrzavanjaDTO mestoOdrzavanjaDTO = new MestoOdrzavanjaDTO();
        mestoOdrzavanjaDTO.setIdMestoOdrzavanja(domainEntity.getIdMestoOdrzavanja());
        mestoOdrzavanjaDTO.setNaziv(domainEntity.getNaziv());
        mestoOdrzavanjaDTO.setAdresa(domainEntity.getAdresa());
        return mestoOdrzavanjaDTO;
    }

    @Override
    public MestoOdrzavanja toDomainEntity(MestoOdrzavanjaDTO domainDTO) {
        MestoOdrzavanja mestoOdrzavanja = new MestoOdrzavanja();
        mestoOdrzavanja.setIdMestoOdrzavanja(domainDTO.getIdMestoOdrzavanja());
        mestoOdrzavanja.setNaziv(domainDTO.getNaziv());
        mestoOdrzavanja.setAdresa(domainDTO.getAdresa());
        return mestoOdrzavanja;
    }
    
}
