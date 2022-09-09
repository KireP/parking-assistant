package com.n47.hackdayz.parkingassistant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parkingLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("order ASC")
    private Set<Coordinate> coordinates = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parkingLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("order ASC")
    private Set<Availability> availabilities = new LinkedHashSet<>();

    public String getOpeningHours() {
        String day = getDay();
        return this.getAvailabilities().stream()
                .filter(availability -> day.equalsIgnoreCase(availability.getDay().name()))
                .findFirst()
                .map(Availability::getOpeningHour)
                .orElse(null);
    }

    public String getClosingHours() {
        String day = getDay();
        return this.getAvailabilities().stream()
                .filter(availability -> day.equalsIgnoreCase(availability.getDay().name()))
                .findFirst()
                .map(Availability::getClosingHour)
                .orElse(null);
    }

    private String getDay() {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        switch (currentDay) {
            case Calendar.MONDAY:
                return "MONDAY";
            case Calendar.TUESDAY:
                return "TUESDAY";
            case Calendar.WEDNESDAY:
                return "WEDNESDAY";
            case Calendar.THURSDAY:
                return "THURSDAY";
            case Calendar.FRIDAY:
                return "FRIDAY";
            case Calendar.SATURDAY:
                return "SATURDAY";
            default:
                return "SUNDAY";
        }
    }
}
