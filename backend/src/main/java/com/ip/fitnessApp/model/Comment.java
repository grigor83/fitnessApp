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
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "comment_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy. HH:mm")
    private LocalDateTime date;
    @Basic
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonBackReference
    private FitnessProgram program;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
