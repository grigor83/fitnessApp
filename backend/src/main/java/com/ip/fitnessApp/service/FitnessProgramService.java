package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.FitnessProgram;
import com.ip.fitnessApp.model.Kategorija;
import com.ip.fitnessApp.repository.FitnessProgramRepository;
import com.ip.fitnessApp.repository.KategorijaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FitnessProgramService {
    private final FitnessProgramRepository fitnessProgramRepository;
    @Autowired
    public FitnessProgramService(FitnessProgramRepository fitnessProgramRepository) {
        this.fitnessProgramRepository = fitnessProgramRepository;
    }

    public List<FitnessProgram> findAll(){
        return fitnessProgramRepository.findAll();
    }

    public FitnessProgram findById(Integer id){
        return fitnessProgramRepository.findById(id)
                .orElse(null);
    }

    public FitnessProgram createProgram(FitnessProgram fitnessProgram) {
        return fitnessProgramRepository.save(fitnessProgram);
    }

    public FitnessProgram updateProgram(FitnessProgram newProgram, Integer id) {
        FitnessProgram fitnessProgram = fitnessProgramRepository.findById(id)
                .orElse(null);
        if (fitnessProgram == null)
            return null;

        fitnessProgram = newProgram;
        return fitnessProgramRepository.save(fitnessProgram);
    }

    public boolean deleteById(Integer id) {
        Optional<FitnessProgram> fitnessProgram = fitnessProgramRepository.findById(id);
        if (fitnessProgram.isEmpty())
            return false;
        else{
            fitnessProgramRepository.deleteById(id);
            return true;
        }
    }

}
