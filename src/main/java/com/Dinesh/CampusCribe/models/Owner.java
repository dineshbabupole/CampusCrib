package com.Dinesh.CampusCribe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    @Column(unique = true)
    private String mail;

    private String hostelName;

    @OneToOne(mappedBy ="owner",cascade = CascadeType.ALL)
    @JsonIgnore
    private Hostel hostel;
}
