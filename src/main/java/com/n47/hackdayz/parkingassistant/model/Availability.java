package com.n47.hackdayz.parkingassistant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "availability")
@Data
@EqualsAndHashCode(exclude = "parkingLocation")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "opening_hour")
    private String openingHour;

    @Column(name = "closing_hour")
    private String closingHour;

    @Column(name = "day")
    @Enumerated(EnumType.STRING)
    private Day day;

    @ManyToOne
    @JoinColumn(name = "parking_location_id")
    @JsonIgnore
    private ParkingLocation parkingLocation;
}
