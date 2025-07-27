package com.Dinesh.CampusCribe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    @Column(unique = true)
    private String mail;

    private String collegeName;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Reviews> reviews;
}
