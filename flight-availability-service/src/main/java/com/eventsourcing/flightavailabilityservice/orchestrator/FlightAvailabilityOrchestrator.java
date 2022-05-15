package com.eventsourcing.flightavailabilityservice.orchestrator;

import com.eventsourcing.flightavailabilityservice.model.FlightAvailabilityEvent;
import com.eventsourcing.flightavailabilityservice.service.FlightAvailabilityService;
import org.springframework.stereotype.Component;

@Component
public class FlightAvailabilityOrchestrator {

    private final FlightAvailabilityService flightAvailabilityService;

    public FlightAvailabilityOrchestrator(FlightAvailabilityService flightAvailabilityService) {
        this.flightAvailabilityService = flightAvailabilityService;
    }

    public void orchestrate(FlightAvailabilityEvent flightAvailabilityEvent) {
        System.out.println("Checking flight availability");

        boolean flightAvailability = flightAvailabilityService.checkFlightAvailability(flightAvailabilityEvent);
    }

}
