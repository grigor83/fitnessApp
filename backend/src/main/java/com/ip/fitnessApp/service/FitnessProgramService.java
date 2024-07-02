package com.ip.fitnessApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.fitnessApp.exceptions.RecordNotFoundException;
import com.ip.fitnessApp.model.FitnessProgram;
import com.ip.fitnessApp.repository.FitnessProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FitnessProgramService {

    @Value("${upload.path}") // Configure the upload directory path in application.properties
    private String uploadPath;
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
                .orElseThrow(
                        () -> new RecordNotFoundException("Ne postoji fitnes program čiji je id = " + id + "!")
                );
    }

    public FitnessProgram createProgram(String programJson, MultipartFile file) throws IOException {
        if (file != null) {
            Path filePath = Paths.get(System.getProperty("user.dir")+ File.separator + uploadPath + File.separator
                    + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        FitnessProgram fitnessProgram = objectMapper.readValue(programJson, FitnessProgram.class);
        fitnessProgram.setImagePath("http://localhost:8080/" + file.getOriginalFilename());
        if (fitnessProgram.getCreatedAt() == null)
            fitnessProgram.setCreatedAt(LocalDateTime.now());

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
