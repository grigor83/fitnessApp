package com.ip.fitnessApp.model;

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
public class Participation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy. HH:mm")
    @Basic
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private FitnessProgram fitnessProgram;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private User user;

}
