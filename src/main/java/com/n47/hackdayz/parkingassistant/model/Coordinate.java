package com.n47.hackdayz.parkingassistant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "coordinate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = "parkingLocation")
public class Coordinate implements Comparable<Coordinate> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "creation_order")
    @JsonIgnore
    private int order;

    @ManyToOne
    @JoinColumn(name = "parking_location_id")
    @JsonIgnore
    private ParkingLocation parkingLocation;

    @Override
    public int compareTo(Coordinate coordinate) {
        return order - coordinate.getOrder();
    }
}
