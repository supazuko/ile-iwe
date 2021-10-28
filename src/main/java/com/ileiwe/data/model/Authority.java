package com.ileiwe.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Authority {

    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne()
    private LearningParty user;
    @Enumerated(EnumType.STRING)
    private Role authority;
}
