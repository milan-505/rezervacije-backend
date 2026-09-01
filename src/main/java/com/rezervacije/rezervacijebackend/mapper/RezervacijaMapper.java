/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.mapper;

import com.rezervacije.rezervacijebackend.domain.RezervacijaDTO;
import com.rezervacije.rezervacijebackend.model.Dogadjaj;
import com.rezervacije.rezervacijebackend.model.Korisnik;
import com.rezervacije.rezervacijebackend.model.Rezervacija;
import com.rezervacije.rezervacijebackend.model.Sto;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milan
 */
@Component
public class RezervacijaMapper {
    private final KorisnikMapper korisnikMapper;
    private final StoMapper stoMapper;
    private final DogadjajMapper dogadjajMapper;

    public RezervacijaMapper(KorisnikMapper korisnikMapper, StoMapper stoMapper, DogadjajMapper dogadjajMapper) {
        this.korisnikMapper = korisnikMapper;
        this.stoMapper = stoMapper;
        this.dogadjajMapper = dogadjajMapper;
    }
    
    public RezervacijaDTO toRezervacijaDTO(Rezervacija rezervacija){
        if(rezervacija==null)return null;
        RezervacijaDTO rezervacijaDTO = new RezervacijaDTO();
        rezervacijaDTO.setIdRezervacija(rezervacija.getIdRezervacija());
        rezervacijaDTO.setStatus(rezervacija.getStatus());
        rezervacijaDTO.setBrojGostiju(rezervacija.getBrojGostiju());
        rezervacijaDTO.setDatumRezervacije(rezervacija.getDatumRezervacije());
        if(rezervacija.getKorisnik()!=null)
        rezervacijaDTO.setKorisnik(korisnikMapper.toDomainDTO(rezervacija.getKorisnik()));
        if(rezervacija.getDogadjaj()!=null)
        rezervacijaDTO.setDogadjaj(dogadjajMapper.toDogadjajDTO(rezervacija.getDogadjaj()));
        if(rezervacija.getSto()!=null)
        rezervacijaDTO.setSto(stoMapper.toStoDTO(rezervacija.getSto()));
        if(rezervacija.getAdmin()!=null)
        rezervacijaDTO.setAdmin(korisnikMapper.toDomainDTO(rezervacija.getAdmin()));
        return rezervacijaDTO;
    }
    
    public Rezervacija toRezervacijaEntity(RezervacijaDTO rezervacijaDTO, Korisnik korisnik,
                                            Dogadjaj dogadjaj, Sto sto, Korisnik admin ){
        if(rezervacijaDTO==null)return null;
        
        Rezervacija rezervacija = new Rezervacija();
        if(rezervacijaDTO.getIdRezervacija()!=null)rezervacija.setIdRezervacija(rezervacijaDTO.getIdRezervacija());
        rezervacija.setStatus(rezervacijaDTO.getStatus());
        rezervacija.setBrojGostiju(rezervacijaDTO.getBrojGostiju());
        rezervacija.setDatumRezervacije(rezervacijaDTO.getDatumRezervacije());
        rezervacija.setKorisnik(korisnik);
        rezervacija.setDogadjaj(dogadjaj);
        rezervacija.setSto(sto);
        rezervacija.setAdmin(admin);
        
        return rezervacija;
        
        
    }
}
