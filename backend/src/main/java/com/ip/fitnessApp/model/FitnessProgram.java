package com.ip.fitnessApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FitnessProgram {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "program_name")
    private String programName;
    @Basic
    @Column(name = "program_description")
    private String description;
    @Basic
    @Column(name = "image_path")
    private String imagePath;
    @Basic
    @Column(name = "duration")
    private String duration;
    @Basic
    @Column(name = "intensity")
    private Integer intensity;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "instructor_name")
    private String instructorName;
    @Basic
    @Column(name = "instructor_contact")
    private String instructorContact;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "program", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //cascade = CascadeType.ALL znaci da kad obrisem program iz baze, brisu se i svi komentari iz baze,
    // jer je program parent a komentari su child
    @JsonManagedReference
    private List<Comment> comments;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
