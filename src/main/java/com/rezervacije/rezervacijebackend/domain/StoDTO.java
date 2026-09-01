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
public class StoDTO implements Serializable, DomainDTO {
    private Long idSto;
    private String oznaka;
    private int kapacitet;
    private ProstorijaDTO prostorija;

    public StoDTO() {
    }

    public StoDTO(Long idSto, String oznaka, int kapacitet, ProstorijaDTO prostorija) {
        this.idSto = idSto;
        this.oznaka = oznaka;
        this.kapacitet = kapacitet;
        this.prostorija = prostorija;
    }

    public Long getIdSto() {
        return idSto;
    }

    public void setIdSto(Long idSto) {
        this.idSto = idSto;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public ProstorijaDTO getProstorija() {
        return prostorija;
    }

    public void setProstorija(ProstorijaDTO prostorija) {
        this.prostorija = prostorija;
    }

    @Override
    public String toString() {
        return "StoDTO{" + "idSto=" + idSto + ", oznaka=" + oznaka + ", kapacitet=" + kapacitet + ", prostorija=" + prostorija + '}';
    }
    
    
}
