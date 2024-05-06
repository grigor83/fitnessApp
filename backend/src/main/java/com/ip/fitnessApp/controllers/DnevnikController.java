package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Dnevnik;
import com.ip.fitnessApp.service.DnevnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dnevnik")
public class DnevnikController {
    private final DnevnikService dnevnikService;

    @Autowired
    public DnevnikController(DnevnikService dnevnikService) {
        this.dnevnikService = dnevnikService;
    }

    @GetMapping
    public ResponseEntity<List<Dnevnik>> findAll() {
        List<Dnevnik> dnevniks = dnevnikService.findAll();
        return new ResponseEntity<>(dnevniks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dnevnik> findById(@PathVariable Integer id) {
        Dnevnik dnevnik = dnevnikService.findById(id);
        if (dnevnik != null)
            return new ResponseEntity<>(dnevnik, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Dnevnik> save(@RequestBody Dnevnik dnevnik) {
        Dnevnik newDnevnik = dnevnikService.createDnevnik(dnevnik);
        return new ResponseEntity<>(newDnevnik, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dnevnik> updateDnevnik(@PathVariable Integer id, @RequestBody Dnevnik dnevnik) {
        Dnevnik updatedDnevnik = dnevnikService.updateDnevnik(dnevnik, id);

        if (updatedDnevnik != null)
            return new ResponseEntity<>(updatedDnevnik, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (dnevnikService.deleteById(id))
            return new ResponseEntity<>("Dnevnik by id: " + id + " deleted successfully!", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("Dnevnik by id: " + id + " not found!", HttpStatus.NOT_FOUND);
    }


}
