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
@Table(name = "sto")

public class Sto implements Serializable, DomainEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sto", nullable = false)
    private Long idSto;
    @Column(name = "oznaka", nullable = false)
    private String oznaka;
    @Column(name = "kapacitet", nullable = false)
    private int kapacitet;
    @JoinColumn(name = "id_prostorija", referencedColumnName = "id_prostorija")
    @ManyToOne(optional = false)
    private Prostorija prostorija;

    public Sto() {
    }

    public Sto(Long idSto) {
        this.idSto = idSto;
    }

    public Sto(Long idSto, String oznaka, int kapacitet) {
        this.idSto = idSto;
        this.oznaka = oznaka;
        this.kapacitet = kapacitet;
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

    

    public Prostorija getProstorija() {
        return prostorija;
    }

    public void setProstorija(Prostorija prostorija) {
        this.prostorija = prostorija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSto != null ? idSto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sto)) {
            return false;
        }
        Sto other = (Sto) object;
        if ((this.idSto == null && other.idSto != null) || (this.idSto != null && !this.idSto.equals(other.idSto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rezervacije.rezervacijebackend.model.Sto[ idSto=" + idSto + " ]";
    }
    
}
