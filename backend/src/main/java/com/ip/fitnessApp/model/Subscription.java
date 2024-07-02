package com.ip.fitnessApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
//lombok zna da napravi problem kad generise equals i tostring metode, ako imamo bidirekcionalnu vezu
//Zato ovdje navodim exclude
@EqualsAndHashCode(callSuper = false, exclude = { "user", "category"})
@ToString(exclude = { "category", "user"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

}
