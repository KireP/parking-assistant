package com.n47.hackdayz.parkingassistant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "opening_hour")
    private String openingHour;

    @Column(name = "closing_hour")
    private String closingHour;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parkingLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coordinate> coordinates = new ArrayList<>();
}
