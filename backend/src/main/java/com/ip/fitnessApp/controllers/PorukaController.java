package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Poruka;
import com.ip.fitnessApp.service.PorukaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poruka")
public class PorukaController {
    private final PorukaService porukaService;
    @Autowired
    public PorukaController(PorukaService porukaService) {
        this.porukaService = porukaService;
    }

    @GetMapping
    public ResponseEntity<List<Poruka>> findAll() {
        List<Poruka> porukas = porukaService.findAll();
        return new ResponseEntity<>(porukas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poruka> findById(@PathVariable Integer id) {
        Poruka poruka = porukaService.findById(id);
        if (poruka != null)
            return new ResponseEntity<>(poruka, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Poruka> save(@RequestBody Poruka poruka) {
        Poruka newPoruka = porukaService.createPoruka(poruka);
        return new ResponseEntity<>(newPoruka, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poruka> updatePoruka(@PathVariable Integer id, @RequestBody Poruka poruka) {
        Poruka updatedPoruka = porukaService.updatePoruka(poruka, id);

        if (updatedPoruka != null)
            return new ResponseEntity<>(updatedPoruka, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (porukaService.deleteById(id))
            return new ResponseEntity<>("Poruka by id: " + id + " deleted successfully!", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("Poruka by id: " + id + " not found!", HttpStatus.NOT_FOUND);
    }

}
