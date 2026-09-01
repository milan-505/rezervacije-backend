/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rezervacije.rezervacijebackend.service;

import com.rezervacije.rezervacijebackend.domain.KorisnikDTO;
import com.rezervacije.rezervacijebackend.jparepo.KorisnikRepository;
import com.rezervacije.rezervacijebackend.mapper.KorisnikMapper;
import com.rezervacije.rezervacijebackend.model.Korisnik;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Milan
 */
@Service
@Transactional
public class KorisnikService {
    private final KorisnikRepository repository;
    private final KorisnikMapper mapper;
    
    public KorisnikService(KorisnikRepository repository, KorisnikMapper mapper){
        this.mapper=mapper;
        this.repository=repository;
    }
    
    public List<KorisnikDTO> getAll(){
        return repository.findAll().stream().map(mapper::toDomainDTO).collect(Collectors.toList());
    }
    
    public KorisnikDTO getById(Long id){
        return repository.findById(id).map(mapper::toDomainDTO).orElse(null);
    }
    
    public String create(KorisnikDTO dto){
        try{
            if(isBlank(dto.getUsername()) || isBlank(dto.getPassword()) || isBlank(dto.getIme())
                    || isBlank(dto.getPrezime()) || isBlank(dto.getEmail())){
                return "Sva polja su obavezna.";
            }
            if(!dto.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")){
                return "Email adresa nije validna.";
            }
            if(dto.getPassword().length() < 4){
                return "Lozinka mora imati bar 4 karaktera.";
            }
            if(repository.findByUsername(dto.getUsername()).isPresent())
            return "Korisnicko ime je vec zauzeto";
        repository.save(mapper.toDomainEntity(dto));
        return "Korisnik je uspesno registrovan";
        }catch(Exception ex){
            return "Greska prilikom registracije";
        }       
    }

    private boolean isBlank(String s){
        return s == null || s.isBlank();
    }
    
    public KorisnikDTO login(String username, String password){
        Korisnik korisnik = repository.findByUsername(username).orElse(null);
        if(korisnik == null || !korisnik.getPassword().equals(password)){
            return null;
        }
        return mapper.toDomainDTO(korisnik);
    }
}
