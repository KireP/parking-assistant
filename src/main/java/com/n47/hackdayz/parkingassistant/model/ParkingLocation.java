package com.n47.hackdayz.parkingassistant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ParkingLocation {

    List<Cordination> regions;
    int price;
    String zone;
    String company;
    ParkingType type;
    String openingHour;
    String closingHour;
}
