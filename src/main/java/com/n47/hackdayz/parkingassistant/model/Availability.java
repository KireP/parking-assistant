package com.n47.hackdayz.parkingassistant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "availability")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = "parkingLocation")
public class Availability implements Comparable<Availability> {

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

    @Column(name = "creation_order")
    @JsonIgnore
    private int order;

    @ManyToOne
    @JoinColumn(name = "parking_location_id")
    @JsonIgnore
    private ParkingLocation parkingLocation;

    @Override
    public int compareTo(Availability availability) {
        return order - availability.getOrder();
    }
}
