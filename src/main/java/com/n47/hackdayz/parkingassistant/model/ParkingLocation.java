package com.n47.hackdayz.parkingassistant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "parking_location")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ParkingLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "zone")
    private String zone;

    @Column(name = "company")
    private String company;

    @Column(name = "company_phone_number")
    private String companyPhoneNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ParkingType type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parkingLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coordinate> coordinates = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parkingLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Availability> availabilities = new HashSet<>();
}
