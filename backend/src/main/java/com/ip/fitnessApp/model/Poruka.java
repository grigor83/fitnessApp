package com.ip.fitnessApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
//lombok zna da napravi problem kad generise equals i tostring metode, ako imamo bidirekcionalnu vezu
//Zato ovdje navodim exclude
@EqualsAndHashCode(callSuper = false, exclude = { "primalac", "posiljalac"})
@ToString(exclude = { "primalac", "posiljalac"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poruka {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "poruka_id")
    private int porukaId;
    @Basic
    @Column(name = "datum_slanja")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy. HH:mm")
    private LocalDateTime datum;
    @Column(name = "tekst")
    private String tekst;
    @Basic
    @Column(name = "procitana")
    private boolean procitana;

    @ManyToOne()
    @JoinColumn(name = "posiljalac_id")
    //@JsonBackReference(value="posiljalac")
    private Korisnik posiljalac;

    @ManyToOne()
    @JoinColumn(name = "primalac_id")
    //@JsonBackReference(value="primalac")
    private Korisnik primalac;

}
