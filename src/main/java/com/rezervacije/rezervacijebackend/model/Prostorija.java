/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Milan
 */
@Entity
@Table(name = "prostorija")

public class Prostorija implements Serializable, DomainEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prostorija", nullable = false)
    private Long idProstorija;
    @Column(name = "naziv", nullable = false)
    private String naziv;
    @JoinColumn(name = "id_mesto_odrzavanja", referencedColumnName = "id_mesto_odrzavanja")
    @ManyToOne(optional = false)
    private MestoOdrzavanja mestoOdrzavanja;
   

    public Prostorija() {
    }

    public Prostorija(Long idProstorija) {
        this.idProstorija = idProstorija;
    }

    public Prostorija(Long idProstorija, String naziv) {
        this.idProstorija = idProstorija;
        this.naziv = naziv;
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

    public MestoOdrzavanja getMestoOdrzavanja() {
        return mestoOdrzavanja;
    }

    public void setIdMestoOdrzavanja(MestoOdrzavanja mestoOdrzavanja) {
        this.mestoOdrzavanja = mestoOdrzavanja;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProstorija != null ? idProstorija.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prostorija)) {
            return false;
        }
        Prostorija other = (Prostorija) object;
        if ((this.idProstorija == null && other.idProstorija != null) || (this.idProstorija != null && !this.idProstorija.equals(other.idProstorija))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rezervacije.rezervacijebackend.model.Prostorija[ idProstorija=" + idProstorija + " ]";
    }
    
}
