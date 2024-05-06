package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.Korisnik;
import com.ip.fitnessApp.repository.KorisnikRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KorisnikService {
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    public List<Korisnik> findAll(){
        return korisnikRepository.findAll();
    }

    public Korisnik findById(Integer id){
        return korisnikRepository.findById(id)
                .orElse(null);
    }

    public Korisnik createKorisnik(Korisnik korisnik) {
        Korisnik newKorisnik = korisnikRepository.save(korisnik);
        return korisnikRepository.save(korisnik);
    }

    public Korisnik updateKorisnik(Korisnik newKorisnik, Integer id) {
        Korisnik korisnik = korisnikRepository.findById(id)
                .orElse(null);
        if (korisnik == null)
            return null;

        korisnik = newKorisnik;
        return korisnikRepository.save(korisnik);
    }

    public boolean deleteById(Integer id) {
        Optional<Korisnik> korisnik = korisnikRepository.findById(id);
        if (korisnik.isEmpty())
            return false;
        else{
            korisnikRepository.deleteById(id);
            return true;
        }
    }

}
