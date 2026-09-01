/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.domain;

import java.io.Serializable;

/**
 *
 * @author Milan
 */
public class MestoOdrzavanjaDTO implements Serializable, DomainDTO {
    private Long idMestoOdrzavanja;
    private String naziv;
    private String adresa;

    public MestoOdrzavanjaDTO() {
    }

    public MestoOdrzavanjaDTO(Long idMestoOdrzavanja, String naziv, String adresa) {
        this.idMestoOdrzavanja = idMestoOdrzavanja;
        this.naziv = naziv;
        this.adresa = adresa;
    }

    public Long getIdMestoOdrzavanja() {
        return idMestoOdrzavanja;
    }

    public void setIdMestoOdrzavanja(Long idMestoOdrzavanja) {
        this.idMestoOdrzavanja = idMestoOdrzavanja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "MestoOdrzavanjaDTO{" + "idMestoOdrzavanja=" + idMestoOdrzavanja + ", naziv=" + naziv + ", adresa=" + adresa + '}';
    }
    
    
}
