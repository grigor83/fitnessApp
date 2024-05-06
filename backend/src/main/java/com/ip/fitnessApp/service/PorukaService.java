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

    public Poruka createPoruka(Poruka poruka) {
        return porukaRepository.save(poruka);
    }

    public Poruka updatePoruka(Poruka newPoruka, Integer id) {
        Poruka poruka = porukaRepository.findById(id)
                .orElse(null);
        if (poruka == null)
            return null;

        if (newPoruka.getDatum() != null)
            poruka.setDatum(newPoruka.getDatum());
        if (newPoruka.getTekst() != null)
            poruka.setTekst(newPoruka.getTekst());
        poruka.setProcitana(newPoruka.isProcitana());
        if (newPoruka.getPosiljalac() != null){
            Optional<Korisnik> k = korisnikRepository.findById(newPoruka.getPosiljalac()
                    .getKorisnikId());
            k.ifPresent(poruka::setPosiljalac);
        }
        if (newPoruka.getPrimalac() != null){
            Optional<Korisnik> k = korisnikRepository.findById(newPoruka.getPrimalac()
                    .getKorisnikId());
            k.ifPresent(poruka::setPrimalac);
        }

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
