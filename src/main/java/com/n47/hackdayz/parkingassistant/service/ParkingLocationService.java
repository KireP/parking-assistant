package com.n47.hackdayz.parkingassistant.service;

import com.n47.hackdayz.parkingassistant.dto.ParkingLocationDTO;
import com.n47.hackdayz.parkingassistant.model.Coordinate;
import com.n47.hackdayz.parkingassistant.model.ParkingLocation;
import com.n47.hackdayz.parkingassistant.repository.ParkingLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingLocationService {

    private final ParkingLocationRepository parkingLocationRepository;

    public List<ParkingLocation> getAll() {
        return parkingLocationRepository.findAll();
    }

    @Transactional
    public ParkingLocation create(ParkingLocationDTO parkingLocationDTO) {
        var parkingLocation =
                ParkingLocation.builder()
                        .price(parkingLocationDTO.getPrice())
                        .zone(parkingLocationDTO.getZone())
                        .company(parkingLocationDTO.getCompany())
                        .companyPhoneNumber(parkingLocationDTO.getCompanyPhoneNumber())
                        .type(parkingLocationDTO.getType())
                        .openingHour(parkingLocationDTO.getOpeningHour())
                        .closingHour(parkingLocationDTO.getClosingHour())
                        .build();
        parkingLocation.setCoordinates(
                parkingLocationDTO.getCoordinates().stream()
                        .map(coordinateDTO -> Coordinate.builder()
                                .latitude(coordinateDTO.getLatitude())
                                .longitude(coordinateDTO.getLongitude())
                                .parkingLocation(parkingLocation)
                                .build())
                        .collect(Collectors.toList())
        );
        return parkingLocationRepository.save(parkingLocation);
    }

    @Transactional
    public ParkingLocation update(Long id, ParkingLocationDTO parkingLocationDTO) {
        var parkingLocation = parkingLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parking Location not found"));
        parkingLocation.setPrice(parkingLocationDTO.getPrice());
        parkingLocation.setZone(parkingLocationDTO.getZone());
        parkingLocation.setCompany(parkingLocationDTO.getCompany());
        parkingLocation.setCompanyPhoneNumber(parkingLocationDTO.getCompanyPhoneNumber());
        parkingLocation.setType(parkingLocationDTO.getType());
        parkingLocation.setOpeningHour(parkingLocationDTO.getOpeningHour());
        parkingLocation.setClosingHour(parkingLocationDTO.getClosingHour());
        parkingLocation.getCoordinates().clear();
        parkingLocation.getCoordinates().addAll(
                parkingLocationDTO.getCoordinates().stream()
                        .map(coordinateDTO -> Coordinate.builder()
                                .latitude(coordinateDTO.getLatitude())
                                .longitude(coordinateDTO.getLongitude())
                                .parkingLocation(parkingLocation)
                                .build())
                        .collect(Collectors.toList())
        );
        return parkingLocationRepository.save(parkingLocation);
    }

    public void deleteParkingLocation(Long id) {
        parkingLocationRepository.deleteById(id);
    }
}
