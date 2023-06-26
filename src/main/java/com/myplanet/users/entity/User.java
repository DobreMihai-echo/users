package com.myplanet.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hhh")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "profile_picture")
    private String profilePhoto;

    @Column(name = "planted_trees")
    private Long plantedTrees;

    private Integer followerCount;
    private Integer followingCount;
    private Boolean enabled;
    private Boolean accountVerified;
    private Boolean emailVerified;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "follow_users",
            joinColumns = @JoinColumn(name = "followed_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    private List<User> followerUsers = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "followerUsers")
    private List<User> followingUsers = new ArrayList<>();

    @Column(name = "points")
    private Long points;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CarbonFootprint> carbonFootprints = new ArrayList();

    public User( String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email=email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", plantedTrees=" + plantedTrees +
                ", followerCount=" + followerCount +
                ", followingCount=" + followingCount +
                ", enabled=" + enabled +
                ", accountVerified=" + accountVerified +
                ", emailVerified=" + emailVerified +
                ", points=" + points +
                '}';
    }
}
