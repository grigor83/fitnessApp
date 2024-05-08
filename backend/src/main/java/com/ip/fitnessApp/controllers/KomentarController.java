package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Komentar;
import com.ip.fitnessApp.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/komentar")
public class KomentarController {
    private final KomentarService komentarService;

    @Autowired
    public KomentarController(KomentarService komentarService) {
        this.komentarService = komentarService;
    }

    @GetMapping
    public ResponseEntity<List<Komentar>> findAll() {
        List<Komentar> komentars = komentarService.findAll();
        return new ResponseEntity<>(komentars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Komentar> findById(@PathVariable Integer id) {
        Komentar komentar = komentarService.findById(id);
        if (komentar != null)
            return new ResponseEntity<>(komentar, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Komentar> save(@RequestBody Komentar komentar) {
        Komentar newKomentar = komentarService.createKomentar(komentar);
        return new ResponseEntity<>(newKomentar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Komentar> updateKomentar(@PathVariable Integer id, @RequestBody Komentar komentar) {
        Komentar updatedKomentar = komentarService.updateKomentar(komentar, id);

        if (updatedKomentar != null)
            return new ResponseEntity<>(updatedKomentar, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (komentarService.deleteById(id))
            return new ResponseEntity<>("Komentar by id: " + id + " deleted successfully!", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("Komentar by id: " + id + " not found!", HttpStatus.NOT_FOUND);
    }

}
