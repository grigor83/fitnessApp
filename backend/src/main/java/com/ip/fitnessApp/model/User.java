package com.ip.fitnessApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "first_name")
    private String name;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "user_password")
    private String password;
    @Basic
    @Column(name = "verified")
    private boolean verified;
    @Basic
    @Column(name = "councelor")
    private boolean councelor;
    @Basic
    @Column(name = "avatar")
    private String avatar;
    @Basic
    @Column(name = "mail",unique=true)
    private String mail;
    @Basic
    @Column(name = "card_number")
    private String cardNumber;
    //Poruke ce se ucitavati koristeci porukeController, zato user nema listu primljenih poruka.
    //Da user ima listu svojih primljenih poruka koje se ucitavaju prilikom ucitavanja korisnika,
    //ne bih mogao dohvatiti posiljaoca (takodje tip User), jer bi tad u klasi Message morao
    //biti oznacen sa JsonBackreference kako bi se prekinula petlja. Sve ovo ima veze sa serijalizacijom-deserijalizacijom
    //objekata.
/*
    @OneToMany(mappedBy = "primalac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //cascade = CascadeType.ALL znaci da kad obrisem program iz baze, brisu se i svi komentari iz baze,
    // jer je user parent a poruke su child
    @JsonManagedReference(value="primalac")
    private List<Message> primljenePoruke;
*/
}
