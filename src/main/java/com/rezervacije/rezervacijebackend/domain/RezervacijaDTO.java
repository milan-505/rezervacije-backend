/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.domain;

import com.rezervacije.rezervacijebackend.model.StatusRezervacije;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Milan
 */
public class RezervacijaDTO implements Serializable, DomainDTO {
    private Long idRezervacija;
    private StatusRezervacije status;
    private LocalDate datumRezervacije;
    private int brojGostiju;
    private KorisnikDTO korisnik;
    private StoDTO sto;
    private DogadjajDTO dogadjaj;
    private KorisnikDTO admin;

    public RezervacijaDTO() {
    }

    public RezervacijaDTO(Long idRezervacija, StatusRezervacije status, LocalDate datumRezervacije, int brojGostiju, KorisnikDTO korisnik, StoDTO sto, DogadjajDTO dogadjaj, KorisnikDTO admin) {
        this.idRezervacija = idRezervacija;
        this.status = status;
        this.datumRezervacije = datumRezervacije;
        this.brojGostiju = brojGostiju;
        this.korisnik = korisnik;
        this.sto = sto;
        this.dogadjaj = dogadjaj;
        this.admin = admin;
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

    public KorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }

    public StoDTO getSto() {
        return sto;
    }

    public void setSto(StoDTO sto) {
        this.sto = sto;
    }

    public DogadjajDTO getDogadjaj() {
        return dogadjaj;
    }

    public void setDogadjaj(DogadjajDTO dogadjaj) {
        this.dogadjaj = dogadjaj;
    }

    public KorisnikDTO getAdmin() {
        return admin;
    }

    public void setAdmin(KorisnikDTO admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "RezervacijaDTO{" + "idRezervacija=" + idRezervacija + ", status=" + status + ", datumRezervacije=" + datumRezervacije + ", brojGostiju=" + brojGostiju + ", korisnik=" + korisnik + ", sto=" + sto + ", dogadjaj=" + dogadjaj + ", admin=" + admin + '}';
    }
    
    
}
