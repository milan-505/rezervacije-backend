/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Milan
 */
public class DogadjajDTO implements Serializable, DomainDTO {
    private Long idDogadjaj;
    private String naziv;
    private LocalDate datum;
    private String opis;
    private MestoOdrzavanjaDTO mestoOdrzavanja;

    public DogadjajDTO() {
    }

    public DogadjajDTO(Long idDogadjaj, String naziv, LocalDate datum, String opis, MestoOdrzavanjaDTO mestoOdrzavanja) {
        this.idDogadjaj = idDogadjaj;
        this.naziv = naziv;
        this.datum = datum;
        this.opis = opis;
        this.mestoOdrzavanja = mestoOdrzavanja;
    }

    public Long getIdDogadjaj() {
        return idDogadjaj;
    }

    public void setIdDogadjaj(Long idDogadjaj) {
        this.idDogadjaj = idDogadjaj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public MestoOdrzavanjaDTO getMestoOdrzavanja() {
        return mestoOdrzavanja;
    }

    public void setMestoOdrzavanja(MestoOdrzavanjaDTO mestoOdrzavanja) {
        this.mestoOdrzavanja = mestoOdrzavanja;
    }

    @Override
    public String toString() {
        return "DogadjajDTO{" + "idDogadjaj=" + idDogadjaj + ", naziv=" + naziv + ", datum=" + datum + ", opis=" + opis + ", mestoOdrzavanja=" + mestoOdrzavanja + '}';
    }
    
    
}
