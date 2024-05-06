package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.*;
import com.ip.fitnessApp.repository.FitnessProgramRepository;
import com.ip.fitnessApp.repository.KomentarRepository;
import com.ip.fitnessApp.repository.KorisnikRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KomentarService {
    private final KomentarRepository komentarRepository;
    private final FitnessProgramRepository fitnessProgramRepository;
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public KomentarService(KomentarRepository komentarRepository, FitnessProgramRepository fitnessProgramRepository, KorisnikRepository korisnikRepository) {
        this.komentarRepository = komentarRepository;
        this.fitnessProgramRepository = fitnessProgramRepository;
        this.korisnikRepository = korisnikRepository;
    }

    public List<Komentar> findAll(){
        return komentarRepository.findAll();
    }

    public Komentar findById(Integer id){
        return komentarRepository.findById(id)
                .orElse(null);
    }

    public Komentar createKomentar(Komentar komentar) {
        //inventar.setDvorana(dvoranaRepository.findById(inventar.getDvorana().getIdDvorana()).orElse(null));
        return komentarRepository.save(komentar);
    }

    public Komentar updateKomentar(Komentar newKomentar, Integer id) {
        Komentar komentar = komentarRepository.findById(id)
                .orElse(null);
        if (komentar == null)
            return null;

        if (newKomentar.getTekst() != null)
            komentar.setTekst(newKomentar.getTekst());
        if (newKomentar.getDatum() != null)
            komentar.setDatum(newKomentar.getDatum());
        if (newKomentar.getProgram() != null){
            Optional<FitnessProgram> f = fitnessProgramRepository.findById(newKomentar.getProgram()
                    .getProgramId());
            f.ifPresent(komentar::setProgram);
        }
        if (newKomentar.getKorisnik() != null){
            Optional<Korisnik> k = korisnikRepository.findById(newKomentar.getKorisnik()
                    .getKorisnikId());
            k.ifPresent(komentar::setKorisnik);
        }

        return komentarRepository.save(komentar);
    }

    public boolean deleteById(Integer id) {
        Optional<Komentar> komentar = komentarRepository.findById(id);
        if (komentar.isEmpty())
            return false;
        else{
            komentarRepository.deleteById(id);
            return true;
        }
    }

}
