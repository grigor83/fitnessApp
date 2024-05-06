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
public class FitnessProgram {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "program_id")
    private int programId;
    @Basic
    @Column(name = "naziv_programa")
    private String nazivPrograma;
    @Basic
    @Column(name = "opis")
    private String opis;
    @Basic
    @Column(name = "naziv_slike")
    private String nazivSlike;
    @Basic
    @Column(name = "trajanje_treninga")
    private String trajanje;
    @Basic
    @Column(name = "nivo_tezine")
    private Integer nivoTezine;
    @Basic
    @Column(name = "cijena")
    private Integer cijena;
    @Basic
    @Column(name = "lokacija")
    private String lokacija;
    @Basic
    @Column(name = "instruktor_ime")
    private String imeInstruktora;
    @Basic
    @Column(name = "instruktor_kontakt")
    private String kontakt;

    @ManyToOne
    @JoinColumn(name = "kategorija_id")
    private Kategorija kategorija;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Korisnik autor;

    @OneToMany(mappedBy = "program", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //cascade = CascadeType.ALL znaci da kad obrisem program iz baze, brisu se i svi komentari iz baze,
    // jer je program parent a komentari su child
    @JsonManagedReference
    private List<Komentar> komentari;

}
