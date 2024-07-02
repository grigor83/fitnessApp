package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Participation;
import com.ip.fitnessApp.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/participations")
public class ParticipationController {
    private final ParticipationService participationService;

    @Autowired
    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @GetMapping
    public ResponseEntity<List<Participation>> findAll() {
        List<Participation> participations = participationService.findAll();
        return new ResponseEntity<>(participations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Participation> save(@RequestBody Participation participation) {
        Participation newParticipation = participationService.createParticipation(participation);
        return new ResponseEntity<>(newParticipation, HttpStatus.CREATED);
    }

}
