/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rezervacije.rezervacijebackend.mapper;

import com.rezervacije.rezervacijebackend.domain.DomainDTO;
import com.rezervacije.rezervacijebackend.model.DomainEntity;

/**
 *
 * @author Milan
 */
public interface BaseMapper<DTO extends DomainDTO, DB extends DomainEntity> {

    public DTO toDomainDTO(DB domainEntity);

    public DB toDomainEntity(DTO domainDTO);

}
