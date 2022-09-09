package com.n47.hackdayz.parkingassistant.dto;

import com.n47.hackdayz.parkingassistant.model.ParkingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ParkingLocationDTO {

    private Double price;
    private String zone;
    private String company;
    private String companyPhoneNumber;
    private ParkingType type;
    private List<CoordinateDTO> coordinates = new ArrayList<>();
    private List<AvailabilityDTO> availabilities = new ArrayList<>();
}
