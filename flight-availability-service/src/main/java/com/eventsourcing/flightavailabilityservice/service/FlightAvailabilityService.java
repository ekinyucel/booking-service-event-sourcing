package com.eventsourcing.flightavailabilityservice.service;

import com.eventsourcing.flightavailabilityservice.model.FlightAvailabilityEvent;
import com.eventsourcing.flightavailabilityservice.model.Statuses;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FlightAvailabilityService {

    private final Map<String, Integer> flightAvailability = new HashMap<>();

    public FlightAvailabilityService() {
        flightAvailability.put("KL1523", 10);
    }

    public boolean checkFlightAvailability(FlightAvailabilityEvent flightAvailabilityEvent) {
        boolean result = false;

        if (flightAvailabilityEvent.getStatus().equals(Statuses.FLIGHT_PENDING.name())) {
            String flightNumber = flightAvailabilityEvent.getFlightNumber();

            if (flightAvailability.get(flightNumber) > 0) {
                result = true;
            } else {
                System.out.println("there is no available allocated seat in this flight");
            }
        }

        return result;
    }

}
