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
import java.time.LocalDate;

/**
 *
 * @author Milan
 */
@Entity
@Table(name = "dogadjaj")
public class Dogadjaj implements Serializable, DomainEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dogadjaj", nullable = false)
    private Long idDogadjaj;
    @Column(name = "naziv", nullable = false)
    private String naziv;
    @Column(name = "datum", nullable = false)
    private LocalDate datum;
    @Column(name = "opis", nullable = false)
    private String opis;
    @JoinColumn(name = "id_mesto_odrzavanja", referencedColumnName = "id_mesto_odrzavanja")
    @ManyToOne(optional = false)
    private MestoOdrzavanja mestoOdrzavanja;

    public Dogadjaj() {
    }

    public Dogadjaj(Long idDogadjaj) {
        this.idDogadjaj = idDogadjaj;
    }

    public Dogadjaj(Long idDogadjaj, String naziv, LocalDate datum, String opis) {
        this.idDogadjaj = idDogadjaj;
        this.naziv = naziv;
        this.datum = datum;
        this.opis = opis;
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


    public MestoOdrzavanja getMestoOdrzavanja() {
        return mestoOdrzavanja;
    }

    public void setMestoOdrzavanja(MestoOdrzavanja mestoOdrzavanja) {
        this.mestoOdrzavanja = mestoOdrzavanja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDogadjaj != null ? idDogadjaj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dogadjaj)) {
            return false;
        }
        Dogadjaj other = (Dogadjaj) object;
        if ((this.idDogadjaj == null && other.idDogadjaj != null) || (this.idDogadjaj != null && !this.idDogadjaj.equals(other.idDogadjaj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rezervacije.rezervacijebackend.model.Dogadjaj[ idDogadjaj=" + idDogadjaj + " ]";
    }
    
}
