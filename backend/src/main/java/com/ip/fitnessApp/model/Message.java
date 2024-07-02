package com.ip.fitnessApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
//lombok zna da napravi problem kad generise equals i tostring metode, ako imamo bidirekcionalnu vezu
//Zato ovdje navodim exclude
@EqualsAndHashCode(callSuper = false, exclude = { "sender", "reciever"})
@ToString(exclude = { "sender", "reciever"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "sent_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy. HH:mm")
    private LocalDateTime date;

    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "seen")
    private boolean seen;

    @ManyToOne()
    @JoinColumn(name = "sender_id")
    //@JsonBackReference(value="sender_id")
    private User sender;

    @ManyToOne()
    @JoinColumn(name = "reciever_id")
    //@JsonBackReference(value="reciever_id")
    private User reciever;

}
