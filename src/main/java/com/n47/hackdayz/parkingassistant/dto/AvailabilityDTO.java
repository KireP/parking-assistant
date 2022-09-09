package com.n47.hackdayz.parkingassistant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AvailabilityDTO {

    private String openingHour;
    private String closingHour;
    private String day;
}
