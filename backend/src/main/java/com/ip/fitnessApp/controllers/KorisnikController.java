package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Korisnik;
import com.ip.fitnessApp.service.EmailService;
import com.ip.fitnessApp.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/korisnik")
public class KorisnikController {
    private final KorisnikService korisnikService;
    private final EmailService emailService;

    @Autowired
    public KorisnikController(KorisnikService korisnikService, EmailService emailService) {
        this.korisnikService = korisnikService;
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<Korisnik>> findAll() {
        List<Korisnik> korisnici = korisnikService.findAll();
        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Korisnik> findById(@PathVariable Integer id) {
        Korisnik korisnik = korisnikService.findById(id);
        if (korisnik != null)
            return new ResponseEntity<>(korisnik, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("id") String id) {
        Korisnik user = korisnikService.findById(Integer.valueOf(id));
        if (user != null){
            user.setVerifikovan(true);
            korisnikService.updateKorisnik(user, user.getKorisnikId());
            return ResponseEntity.ok("Email verified successfully.");
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Korisnik> registerUser(@RequestBody Korisnik korisnik) {
        if (korisnik.getKorisnikId() == 0){
            Korisnik newKorisnik = korisnikService.createKorisnik(korisnik);
            emailService.sendVerificationEmail(newKorisnik);
            return new ResponseEntity<>(newKorisnik, HttpStatus.CREATED);
        }
        else {
            // U slucaju da je korisnik registrovan ali jos nije aktivirao svoj nalog, treba mu opet poslati email
            emailService.sendVerificationEmail(korisnik);
            return new ResponseEntity<>(korisnik, HttpStatus.ACCEPTED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Korisnik> updateKorisnik(@PathVariable Integer id, @RequestBody Korisnik korisnik) {
        Korisnik updatedKorisnik = korisnikService.updateKorisnik(korisnik, id);

        if (updatedKorisnik != null)
            return new ResponseEntity<>(updatedKorisnik, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (korisnikService.deleteById(id))
            return new ResponseEntity<>("Korisnik by id: " + id + " deleted successfully!", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("Korisnik by id: " + id + " not found!", HttpStatus.NOT_FOUND);
    }

}
