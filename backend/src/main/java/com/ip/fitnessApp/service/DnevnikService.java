package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.*;
import com.ip.fitnessApp.repository.DnevnikRepository;
import com.ip.fitnessApp.repository.FitnessProgramRepository;
import com.ip.fitnessApp.repository.KorisnikRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DnevnikService {
    private final DnevnikRepository dnevnikRepository;
    private final KorisnikRepository korisnikRepository;
    private final FitnessProgramRepository fitnessProgramRepository;

    @Autowired
    public DnevnikService(DnevnikRepository dnevnikRepository, KorisnikRepository korisnikRepository, FitnessProgramRepository fitnessProgramRepository) {
        this.dnevnikRepository = dnevnikRepository;
        this.korisnikRepository = korisnikRepository;
        this.fitnessProgramRepository = fitnessProgramRepository;
    }

    public List<Dnevnik> findAll(){
        return dnevnikRepository.findAll();
    }

    public Dnevnik findById(Integer id){
        return dnevnikRepository.findById(id)
                .orElse(null);
    }

    public Dnevnik createDnevnik(Dnevnik dnevnik) {
        //inventar.setDvorana(dvoranaRepository.findById(inventar.getDvorana().getIdDvorana()).orElse(null));
        return dnevnikRepository.save(dnevnik);
    }

    public Dnevnik updateDnevnik(Dnevnik newDnevnik, Integer id) {
        Dnevnik dnevnik = dnevnikRepository.findById(id)
                .orElse(null);
        if (dnevnik == null)
            return null;

        if (newDnevnik.getRezultati() != null)
            dnevnik.setRezultati(newDnevnik.getRezultati());
        if (newDnevnik.getDatum() != null)
            dnevnik.setDatum(newDnevnik.getDatum());
        if (newDnevnik.getKorisnik() != null){
            Optional<Korisnik> k = korisnikRepository.findById(newDnevnik.getKorisnik().getKorisnikId());
            k.ifPresent(dnevnik::setKorisnik);
        }
        if (newDnevnik.getProgram() != null){
            Optional<FitnessProgram> p = fitnessProgramRepository.findById(newDnevnik.getProgram()
                    .getProgramId());
            p.ifPresent(dnevnik::setProgram);
        }

        return dnevnikRepository.save(dnevnik);
    }

    public boolean deleteById(Integer id) {
        Optional<Dnevnik> dnevnik = dnevnikRepository.findById(id);
        if (dnevnik.isEmpty())
            return false;
        else{
            dnevnikRepository.deleteById(id);
            return true;
        }
    }

}
