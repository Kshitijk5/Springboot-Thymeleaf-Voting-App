package com.voting.Votingapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="part_name",unique = true)
    private String partyName;

    @OneToMany(mappedBy = "party")
    private List<User> users;

}
