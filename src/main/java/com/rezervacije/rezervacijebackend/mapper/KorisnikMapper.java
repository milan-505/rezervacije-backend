/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.mapper;

import com.rezervacije.rezervacijebackend.domain.KorisnikDTO;
import com.rezervacije.rezervacijebackend.model.Korisnik;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milan
 */
@Component
public class KorisnikMapper implements BaseMapper<KorisnikDTO, Korisnik>{

    @Override
    public KorisnikDTO toDomainDTO(Korisnik domainEntity) {
        KorisnikDTO korisnikDTO = new KorisnikDTO();
        korisnikDTO.setIdKorisnik(domainEntity.getIdKorisnik());
        korisnikDTO.setIme(domainEntity.getIme());
        korisnikDTO.setPrezime(domainEntity.getPrezime());
        korisnikDTO.setUsername(domainEntity.getUsername());
        korisnikDTO.setPassword(domainEntity.getPassword());
        korisnikDTO.setEmail(domainEntity.getEmail());
        korisnikDTO.setUloga(domainEntity.getUloga());
        return korisnikDTO;
    }

    @Override
    public Korisnik toDomainEntity(KorisnikDTO domainDTO) {
        Korisnik korisnik = new Korisnik();
        korisnik.setIdKorisnik(domainDTO.getIdKorisnik());
        korisnik.setIme(domainDTO.getIme());
        korisnik.setPrezime(domainDTO.getPrezime());
        korisnik.setUsername(domainDTO.getUsername());
        korisnik.setPassword(domainDTO.getPassword());
        korisnik.setEmail(domainDTO.getEmail());
        korisnik.setUloga(domainDTO.getUloga());
        return korisnik;
        
    }
    
}
