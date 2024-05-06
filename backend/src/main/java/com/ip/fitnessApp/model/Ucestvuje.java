package com.ip.fitnessApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ucestvuje {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ucestvuje_id")
    private int ucestvujeId;
    @Basic
    @Column(name = "nacin_placanja")
    private String nacinPlacanja;
    @Column(name = "datum")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy. HH:mm")
    @Basic
    private LocalDateTime datum;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private FitnessProgram fitnessProgram;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;

}
