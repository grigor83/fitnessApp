package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.FitnessProgram;
import com.ip.fitnessApp.model.Korisnik;
import com.ip.fitnessApp.model.Ucestvuje;
import com.ip.fitnessApp.repository.FitnessProgramRepository;
import com.ip.fitnessApp.repository.KorisnikRepository;
import com.ip.fitnessApp.repository.UcestvujeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UcestvujeService {
    private final UcestvujeRepository ucestvujeRepository;
    private final FitnessProgramRepository fitnessProgramRepository;
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public UcestvujeService(UcestvujeRepository ucestvujeRepository, FitnessProgramRepository fitnessProgramRepository, KorisnikRepository korisnikRepository) {
        this.ucestvujeRepository = ucestvujeRepository;
        this.fitnessProgramRepository = fitnessProgramRepository;
        this.korisnikRepository = korisnikRepository;
    }

    public List<Ucestvuje> findAll(){
        return ucestvujeRepository.findAll();
    }

    public Ucestvuje findById(Integer id){
        return ucestvujeRepository.findById(id)
                .orElse(null);
    }

    public Ucestvuje createUcestvuje(Ucestvuje ucestvuje) {
        return ucestvujeRepository.save(ucestvuje);
    }

    public Ucestvuje updateUcestvuje(Ucestvuje newUcestvuje, Integer id) {
        Ucestvuje ucestvuje = ucestvujeRepository.findById(id)
                .orElse(null);
        if (ucestvuje == null)
            return null;

        if (newUcestvuje.getNacinPlacanja() != null)
            ucestvuje.setNacinPlacanja(newUcestvuje.getNacinPlacanja());
        if (newUcestvuje.getDatum() != null)
            ucestvuje.setDatum(newUcestvuje.getDatum());
        if (newUcestvuje.getFitnessProgram() != null){
            Optional<FitnessProgram> f = fitnessProgramRepository.findById(newUcestvuje.getFitnessProgram()
                    .getProgramId());
            f.ifPresent(ucestvuje::setFitnessProgram);
        }
        if (newUcestvuje.getKorisnik() != null){
            Optional<Korisnik> k = korisnikRepository.findById(newUcestvuje.getKorisnik()
                    .getKorisnikId());
            k.ifPresent(ucestvuje::setKorisnik);
        }

        return ucestvujeRepository.save(ucestvuje);
    }

    public boolean deleteById(Integer id) {
        Optional<Ucestvuje> ucestvuje = ucestvujeRepository.findById(id);
        if (ucestvuje.isEmpty())
            return false;
        else{
            ucestvujeRepository.deleteById(id);
            return true;
        }
    }

}
