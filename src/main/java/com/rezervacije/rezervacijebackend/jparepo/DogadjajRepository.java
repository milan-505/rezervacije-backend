/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rezervacije.rezervacijebackend.jparepo;

import com.rezervacije.rezervacijebackend.model.Dogadjaj;
import com.rezervacije.rezervacijebackend.model.MestoOdrzavanja;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Milan
 */
public interface DogadjajRepository extends JpaRepository<Dogadjaj, Long> {
    List<Dogadjaj> findByMestoOdrzavanja(MestoOdrzavanja mestoOdrzavanja);
    List<Dogadjaj> findByNazivContainingIgnoreCase(String naziv);
}
