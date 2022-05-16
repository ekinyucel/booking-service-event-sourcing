package com.eventsourcing.flightavailabilityservice.orchestrator;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.flightavailabilityservice.service.FlightAvailabilityService;
import org.springframework.stereotype.Component;

@Component
public class FlightAvailabilityOrchestrator {

    private final FlightAvailabilityService flightAvailabilityService;

    public FlightAvailabilityOrchestrator(FlightAvailabilityService flightAvailabilityService) {
        this.flightAvailabilityService = flightAvailabilityService;
    }

    public void orchestrate(Booking booking) {
        System.out.println("Checking flight availability");

        boolean flightAvailability = flightAvailabilityService.checkFlightAvailability(booking);
    }

}
