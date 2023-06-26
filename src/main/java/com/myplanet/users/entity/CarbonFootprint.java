package com.myplanet.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "carbon_footprint")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CarbonFootprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_emision")
    private Double carEmissionCo2;

    @Column(name = "public_transit")
    private Double transitEmissionCo2;

    @Column(name = "plane_emission")
    private Double planeEmissionCo2;

    @Column(name = "energy_emission")
    private Double energyEmissionCo2;

    @Column(name = "food_emission")
    private Double foodEmissionCo2;

    @Column(name = "fuel_emission")
    private Double fuelEmissionCo2;

    @Column(name = "total_emission")
    private Double totalEmissionCo2;

    @Column(name = "killed_trees")
    private Double killedTrees;

    @Column(name = "total_offset")
    private Double totalOffset;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String date;


    @Override
    public String toString() {
        return "CarbonFootprint{" +
                "id=" + id +
                ", carEmissionCo2=" + carEmissionCo2 +
                ", transitEmissionCo2=" + transitEmissionCo2 +
                ", planeEmissionCo2=" + planeEmissionCo2 +
                ", energyEmissionCo2=" + energyEmissionCo2 +
                ", foodEmissionCo2=" + foodEmissionCo2 +
                ", fuelEmissionCo2=" + fuelEmissionCo2 +
                ", totalEmissionCo2=" + totalEmissionCo2 +
                ", killedTrees=" + killedTrees +
                ", totalOffset=" + totalOffset +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
