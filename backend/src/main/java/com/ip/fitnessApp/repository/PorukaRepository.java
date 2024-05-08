package com.ip.fitnessApp.repository;

import com.ip.fitnessApp.model.Poruka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PorukaRepository extends JpaRepository<Poruka, Integer> {

    //@Query("SELECT poruka FROM Poruka poruka WHERE poruka.primalac.korisnikId = :primalacId")
    List<Poruka> findByPrimalacKorisnikId(Integer primalacId);
}
