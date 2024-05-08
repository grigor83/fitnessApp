package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.*;
import com.ip.fitnessApp.repository.KorisnikRepository;
import com.ip.fitnessApp.repository.PorukaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PorukaService {
    private final PorukaRepository porukaRepository;
    private final KorisnikRepository korisnikRepository;

    public PorukaService(PorukaRepository porukaRepository, KorisnikRepository korisnikRepository) {
        this.porukaRepository = porukaRepository;
        this.korisnikRepository = korisnikRepository;
    }

    public List<Poruka> findAll(){
        return porukaRepository.findAll();
    }

    public Poruka findById(Integer id){
        return porukaRepository.findById(id)
                .orElse(null);
    }

    public List<Poruka> findByPrimalacId(Integer primalacId){
        return porukaRepository.findByPrimalacKorisnikId(primalacId);
    }

    public Poruka createPoruka(Poruka poruka) {
        return porukaRepository.save(poruka);
    }

    public Poruka updatePoruka(Poruka newPoruka, Integer id) {
        Poruka poruka = porukaRepository.findById(id)
                .orElse(null);
        if (poruka == null)
            return null;

        poruka = newPoruka;
        return porukaRepository.save(poruka);
    }

    public boolean deleteById(Integer id) {
        Optional<Poruka> poruka = porukaRepository.findById(id);
        if (poruka.isEmpty())
            return false;
        else{
            porukaRepository.deleteById(id);
            return true;
        }
    }

}
