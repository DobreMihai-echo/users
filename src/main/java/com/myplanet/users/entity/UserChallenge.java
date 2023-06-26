package com.myplanet.users.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user-challenge")
@Data
@NoArgsConstructor
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String tile;

    @Column(name = "description")
    private String description;

    @Column(name = "creator")
    private String creator;

    @Column(name = "level")
    private String level;

    @Column(name = "points")
    private Long points;

    @Column(name = "color")
    private String color;

    @Column(name = "username")
    private String username;

    @Column(name = "completed")
    private Boolean completed;
}
