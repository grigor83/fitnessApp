package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Ucestvuje;
import com.ip.fitnessApp.service.UcestvujeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ucestvuje")
public class UcestvujeController {
    private final UcestvujeService ucestvujeService;

    @Autowired
    public UcestvujeController(UcestvujeService ucestvujeService) {
        this.ucestvujeService = ucestvujeService;
    }

    @GetMapping
    public ResponseEntity<List<Ucestvuje>> findAll() {
        List<Ucestvuje> ucestvujes = ucestvujeService.findAll();
        return new ResponseEntity<>(ucestvujes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ucestvuje> findById(@PathVariable Integer id) {
        Ucestvuje ucestvuje = ucestvujeService.findById(id);
        if (ucestvuje != null)
            return new ResponseEntity<>(ucestvuje, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Ucestvuje> save(@RequestBody Ucestvuje ucestvuje) {
        Ucestvuje newUcestvuje = ucestvujeService.createUcestvuje(ucestvuje);
        return new ResponseEntity<>(newUcestvuje, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ucestvuje> updateUcestvuje(@PathVariable Integer id, @RequestBody Ucestvuje ucestvuje) {
        Ucestvuje updatedUcestvuje = ucestvujeService.updateUcestvuje(ucestvuje, id);

        if (updatedUcestvuje != null)
            return new ResponseEntity<>(updatedUcestvuje, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (ucestvujeService.deleteById(id))
            return new ResponseEntity<>("Ucestvuje by id: " + id + " deleted successfully!", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("Ucestvuje by id: " + id + " not found!", HttpStatus.NOT_FOUND);
    }


}
