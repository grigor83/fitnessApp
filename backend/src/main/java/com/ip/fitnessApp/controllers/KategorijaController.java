package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Kategorija;
import com.ip.fitnessApp.service.KategorijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/kategorija")
public class KategorijaController {
    private final KategorijaService kategorijaService;
    @Autowired
    public KategorijaController(KategorijaService kategorijaService) {
        this.kategorijaService = kategorijaService;
    }

    @GetMapping
    public ResponseEntity<List<Kategorija>> findAll() {
        List<Kategorija> kategorijas = kategorijaService.findAll();
        return new ResponseEntity<>(kategorijas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kategorija> findById(@PathVariable Integer id) {
        Kategorija kategorija = kategorijaService.findById(id);
        if (kategorija != null)
            return new ResponseEntity<>(kategorija, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Kategorija> save(@RequestBody Kategorija kategorija) {
        Kategorija newKategorija = kategorijaService.createKategorija(kategorija);
        return new ResponseEntity<>(newKategorija, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kategorija> updateKategorija(@PathVariable Integer id, @RequestBody Kategorija kategorija) {
        Kategorija updatedKategorija = kategorijaService.updateKategorija(kategorija, id);

        if (updatedKategorija != null)
            return new ResponseEntity<>(updatedKategorija, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (kategorijaService.deleteById(id))
            return new ResponseEntity<>("Kategorija by id: " + id + " deleted successfully!", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("Kategorija by id: " + id + " not found!", HttpStatus.NOT_FOUND);
    }

}
