package com.n47.hackdayz.parkingassistant.api;

import com.n47.hackdayz.parkingassistant.dto.ParkingLocationDTO;
import com.n47.hackdayz.parkingassistant.model.ParkingLocation;
import com.n47.hackdayz.parkingassistant.service.ParkingLocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-locations")
@AllArgsConstructor
public class ParkingAssistantController {

    private final ParkingLocationService parkingLocationService;

    @GetMapping
    public List<ParkingLocation> getAllLocation() {
        return parkingLocationService.getAll();
    }

    @PostMapping
    public ParkingLocation createParkingLocation(@RequestBody ParkingLocationDTO parkingLocationDTO) {
        return parkingLocationService.create(parkingLocationDTO);
    }

    @PutMapping(path = "/{id}")
    public ParkingLocation updateParkingLocation(@PathVariable Long id, @RequestBody ParkingLocationDTO parkingLocationDTO) {
        return parkingLocationService.update(id, parkingLocationDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteParkingLocation(@PathVariable Long id) {
        parkingLocationService.deleteParkingLocation(id);
    }
}
