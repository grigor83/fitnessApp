package com.ip.fitnessApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Komentar {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "komentar_id")
    private int komentarId;
    @Column(name = "datum")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy. HH:mm")
    @Basic
    private LocalDateTime datum;
    @Basic
    @Column(name = "tekst")
    private String tekst;

    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonBackReference
    private FitnessProgram program;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

}
