package com.ip.fitnessApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Korisnik {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "korisnik_id")
    private int korisnikId;
    @Basic
    @Column(name = "ime")
    private String ime;
    @Basic
    @Column(name = "grad")
    private String grad;
    @Basic
    @Column(name = "korisnicko_ime")
    private String korisnickoIme;
    @Basic
    @Column(name = "lozinka")
    private String lozinka;
    @Basic
    @Column(name = "verifikovan")
    private boolean verifikovan;
    @Basic
    @Column(name = "savjetnik")
    private boolean savjetnik;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "mejl",unique=true)
    private String mejl;
    @Basic
    @Column(name = "broj_kartice")
    private String brojKartice;
    //Poruke ce se ucitavati koristeci porukeController, zato korisnik nema listu primljenih poruka.
    //Da korisnik ima listu svojih primljenih poruka koje se ucitavaju prilikom ucitavanja korisnika,
    //ne bih mogao dohvatiti posiljaoca (takodje tip Korisnik), jer bi tad u klasi Poruka morao
    //biti oznacen sa JsonBackreference kako bi se prekinula petlja. Sve ovo ima veze sa serijalizacijom-deserijalizacijom
    //objekata.
/*
    @OneToMany(mappedBy = "primalac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //cascade = CascadeType.ALL znaci da kad obrisem program iz baze, brisu se i svi komentari iz baze,
    // jer je korisnik parent a poruke su child
    @JsonManagedReference(value="primalac")
    private List<Poruka> primljenePoruke;
*/
    @ManyToOne
    @JoinColumn(name = "pretplata")
    private Kategorija pretplata;

}
