/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "rezervacija")

public class Rezervacija implements Serializable, DomainEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rezervacija", nullable = false)
    private Long idRezervacija;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusRezervacije status;
    @Column(name = "datum_rezervacije", nullable = false)
    private LocalDate datumRezervacije;
    @Column(name = "broj_gostiju",nullable = false)
    private int brojGostiju;
    @JoinColumn(name = "id_korisnik", referencedColumnName = "id_korisnik")
    @ManyToOne(optional = false)
    private Korisnik korisnik;
    @JoinColumn(name = "id_sto", referencedColumnName = "id_sto")
    @ManyToOne(optional = false)
    private Sto sto;
    @JoinColumn(name = "id_dogadjaj", referencedColumnName = "id_dogadjaj")
    @ManyToOne(optional = false)
    private Dogadjaj dogadjaj;
    @JoinColumn(name = "id_admin", referencedColumnName = "id_korisnik")
    @ManyToOne(optional = true)
    private Korisnik admin;

    public Rezervacija() {
    }

    public Rezervacija(Long idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public Rezervacija(Long idRezervacija, StatusRezervacije status, LocalDate datumRezervacije, int brojGostiju) {
        this.idRezervacija = idRezervacija;
        this.status = status;
        this.datumRezervacije = datumRezervacije;
        this.brojGostiju = brojGostiju;
    }

    public Long getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(Long idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public StatusRezervacije getStatus() {
        return status;
    }

    public void setStatus(StatusRezervacije status) {
        this.status = status;
    }

    public LocalDate getDatumRezervacije() {
        return datumRezervacije;
    }

    public void setDatumRezervacije(LocalDate datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public int getBrojGostiju() {
        return brojGostiju;
    }

    public void setBrojGostiju(int brojGostiju) {
        this.brojGostiju = brojGostiju;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Sto getSto() {
        return sto;
    }

    public void setSto(Sto sto) {
        this.sto = sto;
    }

    public Dogadjaj getDogadjaj() {
        return dogadjaj;
    }

    public void setDogadjaj(Dogadjaj dogadjaj) {
        this.dogadjaj = dogadjaj;
    }

    public Korisnik getAdmin() {
        return admin;
    }

    public void setAdmin(Korisnik admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRezervacija != null ? idRezervacija.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervacija)) {
            return false;
        }
        Rezervacija other = (Rezervacija) object;
        if ((this.idRezervacija == null && other.idRezervacija != null) || (this.idRezervacija != null && !this.idRezervacija.equals(other.idRezervacija))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rezervacije.rezervacijebackend.model.Rezervacija[ idRezervacija=" + idRezervacija + " ]";
    }
    
}
