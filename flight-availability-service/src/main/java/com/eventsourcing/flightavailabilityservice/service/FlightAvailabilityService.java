package com.eventsourcing.flightavailabilityservice.service;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.bookingservice.model.Statuses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FlightAvailabilityService {

    private final Logger logger = LoggerFactory.getLogger(FlightAvailabilityService.class);

    private final Map<String, Integer> flightAvailability = new HashMap<>();

    public FlightAvailabilityService() {
        flightAvailability.put("KL1523", 10);
    }

    public boolean checkFlightAvailability(Booking booking) {
        boolean result = false;

        if (booking.getStatus().equals(Statuses.FLIGHT_PENDING.name())) {
            String flightNumber = booking.getFlightNumber();

            // TODO handle exception handling. whe npe or any exception occurs kafka library keeps trying to enter the processing logic
            int availableSeats = flightAvailability.getOrDefault(flightNumber, 0);
            if (availableSeats > 0) {
                result = true;
                logger.info("there is available seat in this flight");
            } else {
                logger.info("there is no available allocated seat in this flight");
            }
        }

        return result;
    }

}
