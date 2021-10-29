package com.ileiwe.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotBlank
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    private Gender gender;
    private String specialization;
    @Column(length = 1000)
    private String bio;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private LearningParty learningParty;
    @OneToMany
    private List<Course> courses;

}
