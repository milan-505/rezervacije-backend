/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rezervacije.rezervacijebackend.jparepo;

import com.rezervacije.rezervacijebackend.model.Dogadjaj;
import com.rezervacije.rezervacijebackend.model.Korisnik;
import com.rezervacije.rezervacijebackend.model.Rezervacija;
import com.rezervacije.rezervacijebackend.model.StatusRezervacije;
import com.rezervacije.rezervacijebackend.model.Sto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Milan
 */
public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    List<Rezervacija> findByStatus(StatusRezervacije status);
    Page<Rezervacija> findByStatus(StatusRezervacije status, Pageable pageable);
    List<Rezervacija> findByKorisnik(Korisnik korisnik);
    List<Rezervacija> findByDogadjaj(Dogadjaj dogadjaj);
    List<Rezervacija> findBySto(Sto sto);
    List<Rezervacija> findByStoAndDogadjajAndStatusIn(Sto sto, Dogadjaj dogadjaj, List<StatusRezervacije> statuses);
}
