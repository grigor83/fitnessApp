package com.ip.fitnessApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ip.fitnessApp.model.FitnessProgram;
import com.ip.fitnessApp.repository.FitnessProgramRepository;
import com.ip.fitnessApp.service.FitnessProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/programs")
public class FitnessProgramController {

    @Value("${upload.path}") // Configure the upload directory path in application.properties
    private String uploadPath;
    private final FitnessProgramService fitnessProgramService;
    private final FitnessProgramRepository fitnessProgramRepository;

    @Autowired
    public FitnessProgramController(FitnessProgramService fitnessProgramService, FitnessProgramRepository fitnessProgramRepository) {
        this.fitnessProgramService = fitnessProgramService;
        this.fitnessProgramRepository = fitnessProgramRepository;
    }

    @GetMapping
    public ResponseEntity<List<FitnessProgram>> findAll() {
        List<FitnessProgram> fitnessPrograms = fitnessProgramService.findAll();
        return new ResponseEntity<>(fitnessPrograms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FitnessProgram> findById(@PathVariable Integer id) {
        FitnessProgram fitnessProgram = fitnessProgramService.findById(id);
        if (fitnessProgram != null)
            return new ResponseEntity<>(fitnessProgram, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/paginated")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Page<FitnessProgram>> getPaginatedCards(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FitnessProgram> cards = fitnessProgramRepository.findAll(pageable);
        return ResponseEntity.ok(cards);
    }

    @PostMapping()
    public ResponseEntity<FitnessProgram> createProgram(@RequestParam("program") String programJson,
                                                        @RequestPart("image") MultipartFile file) throws IOException {

        FitnessProgram newFitnessProgram = fitnessProgramService.createProgram(programJson, file);
        System.out.println(newFitnessProgram.getImagePath());

        return new ResponseEntity<>(newFitnessProgram, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<FitnessProgram> updateProgramWithImage(@RequestParam("program") String programJson,
                                                        @RequestPart("image") MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        FitnessProgram fitnessProgram = objectMapper.readValue(programJson, FitnessProgram.class);

        if (file != null) {
            Path filePath = Paths.get(System.getProperty("user.dir")+ File.separator + uploadPath + File.separator
                    + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            fitnessProgram.setImagePath("http://localhost:8080/" + file.getOriginalFilename());
        }

        FitnessProgram updatedProgram = fitnessProgramService.updateProgram(fitnessProgram, fitnessProgram.getId());
        System.out.println(updatedProgram.getImagePath());
        return new ResponseEntity<>(updatedProgram, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //Postoje dva endpointa za update programa, ovaj je za slucaj da se na frontendu ne promijeni fotografija
    public ResponseEntity<FitnessProgram> updateProgram(@PathVariable Integer id, @RequestBody FitnessProgram newProgram) {
        FitnessProgram updatedProgram = fitnessProgramService.updateProgram(newProgram, id);

        if (updatedProgram != null)
            return new ResponseEntity<>(updatedProgram, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (fitnessProgramService.deleteById(id))
            return ResponseEntity.ok().build();
            //return new ResponseEntity<>("Fitness program by id: " + id + " deleted successfully!", HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
            //return new ResponseEntity<>("Fitness program by id: " + id + " not found!", HttpStatus.NOT_FOUND);
    }

}
