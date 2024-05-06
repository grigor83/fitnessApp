package com.ip.fitnessApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "mejl",unique=true)
    private String mejl;
    @Basic
    @Column(name = "broj_kartice")
    private String brojKartice;

    @ManyToOne
    @JoinColumn(name = "pretplata")
    private Kategorija pretplata;

}
