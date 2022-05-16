package com.eventsourcing.flightavailabilityservice.orchestrator;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.bookingservice.model.Statuses;
import com.eventsourcing.flightavailabilityservice.producer.FlightAvailabilityProducer;
import com.eventsourcing.flightavailabilityservice.service.FlightAvailabilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FlightAvailabilityOrchestrator {

    private final Logger logger = LoggerFactory.getLogger(FlightAvailabilityOrchestrator.class);
    private final FlightAvailabilityService flightAvailabilityService;
    private final FlightAvailabilityProducer flightAvailabilityProducer;

    public FlightAvailabilityOrchestrator(FlightAvailabilityService flightAvailabilityService,
                                          FlightAvailabilityProducer flightAvailabilityProducer) {
        this.flightAvailabilityService = flightAvailabilityService;
        this.flightAvailabilityProducer = flightAvailabilityProducer;
    }

    public void orchestrate(Booking booking) {
        logger.info("Checking flight availability");

        boolean flightAvailability = flightAvailabilityService.checkFlightAvailability(booking);

        if (flightAvailability) {
            booking.setStatus(Statuses.FLIGHT_AVAILABLE.name());
        } else {
            booking.setStatus(Statuses.FLIGHT_NOT_AVAILABLE.name());
        }

        flightAvailabilityProducer.produceEvent(booking);
    }

}
