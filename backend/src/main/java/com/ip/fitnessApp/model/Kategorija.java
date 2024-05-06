package com.ip.fitnessApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kategorija {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "kategorija_id")
    private int kategorijaId;
    @Basic
    @Column(name = "naziv_kategorije")
    private String nazivKategorije;

    @ManyToOne
    @JoinColumn(name = "atribut_id")
    private Atribut atribut;

}
