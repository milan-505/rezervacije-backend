/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rezervacije.rezervacijebackend.jparepo;

import com.rezervacije.rezervacijebackend.model.Prostorija;
import com.rezervacije.rezervacijebackend.model.Sto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Milan
 */
public interface StoRepository extends JpaRepository<Sto, Long> {
    List<Sto> findByProstorija(Prostorija prostorija);
}
