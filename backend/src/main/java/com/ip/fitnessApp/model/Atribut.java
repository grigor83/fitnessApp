package com.ip.fitnessApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atribut {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "atribut_id")
    private int atributId;
    @Basic
    @Column(name = "naziv_atributa")
    private String nazivAtributa;

}
