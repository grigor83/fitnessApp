package com.ip.fitnessApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
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
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "hh:mm dd.MM.yyyy.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm dd.MM.yyyy.")
    private Timestamp datum;
    @Column(name = "tekst")
    private String tekst;
    @Basic
    @Column(name = "procitana")
    private boolean procitana;

    @ManyToOne
    @JoinColumn(name = "posiljalac_id")
    private Korisnik posiljalac;

    @ManyToOne
    @JoinColumn(name = "primalac_id")
    private Korisnik primalac;

}
