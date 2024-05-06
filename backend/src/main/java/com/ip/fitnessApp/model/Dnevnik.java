package com.ip.fitnessApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dnevnik {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "zapis_id")
    private int zapisId;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "dd.MM.yyyy.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy.")
    private Date datum;
    @Basic
    @Column(name = "rezultati")
    private Integer rezultati;

    @ManyToOne
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;
    @ManyToOne
    @JoinColumn(name = "program_id")
    private FitnessProgram program;

}
