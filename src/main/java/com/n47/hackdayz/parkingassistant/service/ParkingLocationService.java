package com.n47.hackdayz.parkingassistant.service;

import com.n47.hackdayz.parkingassistant.dto.ParkingLocationDTO;
import com.n47.hackdayz.parkingassistant.model.Availability;
import com.n47.hackdayz.parkingassistant.model.Coordinate;
import com.n47.hackdayz.parkingassistant.model.Day;
import com.n47.hackdayz.parkingassistant.model.ParkingLocation;
import com.n47.hackdayz.parkingassistant.repository.ParkingLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ParkingLocationService {

    private final ParkingLocationRepository parkingLocationRepository;

    public List<ParkingLocation> getAll() {
        return parkingLocationRepository.findAll();
    }

    @Transactional
    public ParkingLocation create(ParkingLocationDTO parkingLocationDTO) {
        var parkingLocation = ParkingLocation.builder()
                .coordinates(new LinkedHashSet<>())
                .availabilities(new LinkedHashSet<>())
                .build();
        mapToParkingLocation(parkingLocation, parkingLocationDTO);
        return parkingLocationRepository.save(parkingLocation);
    }

    @Transactional
    public ParkingLocation update(Long id, ParkingLocationDTO parkingLocationDTO) {
        var parkingLocation = parkingLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking location not found"));
        mapToParkingLocation(parkingLocation, parkingLocationDTO);
        return parkingLocationRepository.save(parkingLocation);
    }

    private void mapToParkingLocation(ParkingLocation parkingLocation, ParkingLocationDTO parkingLocationDTO) {
        if (nonNull(parkingLocationDTO.getPrice())) {
            parkingLocation.setPrice(parkingLocationDTO.getPrice());
        }
        if (nonNull(parkingLocationDTO.getZone())) {
            parkingLocation.setZone(parkingLocationDTO.getZone());
        }
        if (nonNull(parkingLocationDTO.getCompany())) {
            parkingLocation.setCompany(parkingLocationDTO.getCompany());
        }
        if (nonNull(parkingLocationDTO.getCompanyPhoneNumber())) {
            parkingLocation.setCompanyPhoneNumber(parkingLocationDTO.getCompanyPhoneNumber());
        }
        if (nonNull(parkingLocationDTO.getType())) {
            parkingLocation.setType(parkingLocationDTO.getType());
        }
        if (nonNull(parkingLocationDTO.getZone())) {
            parkingLocation.setZone(parkingLocationDTO.getZone());
        }
        setAvailabilities(parkingLocation, parkingLocationDTO);
        setCoordinates(parkingLocation, parkingLocationDTO);
    }

    private void setCoordinates(ParkingLocation parkingLocation, ParkingLocationDTO parkingLocationDTO) {
        var coordinates = new ArrayList<Coordinate>();
        for (int i = 0; i < parkingLocationDTO.getCoordinates().size(); i++) {
            var coordinateDTO = parkingLocationDTO.getCoordinates().get(i);
            coordinates.add(Coordinate.builder()
                    .latitude(coordinateDTO.getLatitude())
                    .longitude(coordinateDTO.getLongitude())
                    .parkingLocation(parkingLocation)
                    .order(i)
                    .build());
        }
        parkingLocation.getCoordinates().clear();
        parkingLocation.getCoordinates().addAll(coordinates);
    }

    private void setAvailabilities(ParkingLocation parkingLocation, ParkingLocationDTO parkingLocationDTO) {
        var availabilities = new ArrayList<Availability>();
        for (int i = 0; i < parkingLocationDTO.getAvailabilities().size(); i++) {
            var availabilityDTO = parkingLocationDTO.getAvailabilities().get(i);
            availabilities.add(Availability.builder()
                    .day(Day.valueOf(availabilityDTO.getDay()))
                    .openingHour(availabilityDTO.getOpeningHour())
                    .closingHour(availabilityDTO.getClosingHour())
                    .parkingLocation(parkingLocation)
                    .order(i)
                    .build());
        }
        parkingLocation.getAvailabilities().clear();
        parkingLocation.getAvailabilities().addAll(availabilities);
    }

    public void deleteParkingLocation(Long id) {
        parkingLocationRepository.deleteById(id);
    }
}
