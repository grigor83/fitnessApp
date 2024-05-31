package com.ip.fitnessApp.repository;


import com.ip.fitnessApp.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

    boolean existsByKorisnickoIme(String korisnickoIme);
}
