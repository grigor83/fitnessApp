package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Atribut;
import com.ip.fitnessApp.service.AtributService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atribut")
public class AtributController {
    private final AtributService atributService;
    @Autowired
    public AtributController(AtributService atributService) {
        this.atributService = atributService;
    }

    @GetMapping
    public ResponseEntity<List<Atribut>> findAll() {
        List<Atribut> atributs = atributService.findAll();
        return new ResponseEntity<>(atributs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atribut> findById(@PathVariable Integer id) {
        Atribut atribut = atributService.findById(id);
        if (atribut != null)
            return new ResponseEntity<>(atribut, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Atribut> save(@RequestBody Atribut atribut) {
        Atribut newAtribut = atributService.createAtribut(atribut);
        return new ResponseEntity<>(newAtribut, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atribut> updateAtribut(@PathVariable Integer id, @RequestBody Atribut atribut) {
        Atribut updatedAtribut = atributService.updateAtribut(atribut, id);

        if (updatedAtribut != null)
            return new ResponseEntity<>(updatedAtribut, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (atributService.deleteById(id))
            return new ResponseEntity<>("Atribut by id: " + id + " deleted successfully!", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("Atribut by id: " + id + " not found!", HttpStatus.NOT_FOUND);
    }


}
