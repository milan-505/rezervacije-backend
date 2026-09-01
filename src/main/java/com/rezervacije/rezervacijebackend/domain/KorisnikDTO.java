/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.domain;

import com.rezervacije.rezervacijebackend.model.Uloga;
import java.io.Serializable;

/**
 *
 * @author Milan
 */
public class KorisnikDTO implements Serializable, DomainDTO {
    private Long idKorisnik;
    private String username;
    private String password;
    private String ime;
    private String prezime;
    private String email;
    private Uloga uloga;

    public KorisnikDTO() {
    }

    public KorisnikDTO(Long idKorisnik, String username, String password, String ime, String prezime, String email, Uloga uloga) {
        this.idKorisnik = idKorisnik;
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.uloga = uloga;
    }

    public Long getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Long idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    @Override
    public String toString() {
        return "KorisnikDTO{" + "idKorisnik=" + idKorisnik + ", username=" + username + ", password=" + password + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", uloga=" + uloga + '}';
    }
    
    
}
