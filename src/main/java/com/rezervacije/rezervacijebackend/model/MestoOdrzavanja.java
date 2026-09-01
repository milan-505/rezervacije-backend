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
import jakarta.persistence.Table;

/**
 *
 * @author Milan
 */
@Entity
@Table(name = "mesto_odrzavanja")
public class MestoOdrzavanja implements Serializable, DomainEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesto_odrzavanja", nullable = false)
    private Long idMestoOdrzavanja;
    @Column(name = "naziv", nullable = false)
    private String naziv;
    @Column(name = "adresa", nullable = false)
    private String adresa;
    

    public MestoOdrzavanja() {
    }

    public MestoOdrzavanja(Long idMestoOdrzavanja) {
        this.idMestoOdrzavanja = idMestoOdrzavanja;
    }

    public MestoOdrzavanja(Long idMestoOdrzavanja, String naziv, String adresa) {
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
    public int hashCode() {
        int hash = 0;
        hash += (idMestoOdrzavanja != null ? idMestoOdrzavanja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MestoOdrzavanja)) {
            return false;
        }
        MestoOdrzavanja other = (MestoOdrzavanja) object;
        if ((this.idMestoOdrzavanja == null && other.idMestoOdrzavanja != null) || (this.idMestoOdrzavanja != null && !this.idMestoOdrzavanja.equals(other.idMestoOdrzavanja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rezervacije.rezervacijebackend.model.MestoOdrzavanja[ idMestoOdrzavanja=" + idMestoOdrzavanja + " ]";
    }
    
}
