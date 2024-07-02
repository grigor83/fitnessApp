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
public class Diary {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "record_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "dd.MM.yyyy.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy.")
    private Date recordDate;
    @Basic
    @Column(name = "results")
    private Integer results;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "program_id")
    private FitnessProgram program;

}
