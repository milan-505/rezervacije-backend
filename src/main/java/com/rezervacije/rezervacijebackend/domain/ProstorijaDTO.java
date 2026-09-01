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
public class ProstorijaDTO implements Serializable, DomainDTO {
    private Long idProstorija;
    private String naziv;
    private MestoOdrzavanjaDTO mestoOdrzavanja;

    public ProstorijaDTO() {
    }

    public ProstorijaDTO(Long idProstorija, String naziv, MestoOdrzavanjaDTO mestoOdrzavanja) {
        this.idProstorija = idProstorija;
        this.naziv = naziv;
        this.mestoOdrzavanja = mestoOdrzavanja;
    }

    public Long getIdProstorija() {
        return idProstorija;
    }

    public void setIdProstorija(Long idProstorija) {
        this.idProstorija = idProstorija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public MestoOdrzavanjaDTO getMestoOdrzavanja() {
        return mestoOdrzavanja;
    }

    public void setMestoOdrzavanja(MestoOdrzavanjaDTO mestoOdrzavanja) {
        this.mestoOdrzavanja = mestoOdrzavanja;
    }

    @Override
    public String toString() {
        return "ProstorijaDTO{" + "idProstorija=" + idProstorija + ", naziv=" + naziv + ", mestoOdrzavanja=" + mestoOdrzavanja + '}';
    }
    
    
    
}
