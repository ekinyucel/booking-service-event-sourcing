package com.eventsourcing.flightavailabilityservice.consumer;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.flightavailabilityservice.orchestrator.FlightAvailabilityOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FlightAvailabilityConsumer {

    private final Logger logger = LoggerFactory.getLogger(FlightAvailabilityConsumer.class);
    private final FlightAvailabilityOrchestrator flightAvailabilityOrchestrator;

    public FlightAvailabilityConsumer(FlightAvailabilityOrchestrator flightAvailabilityOrchestrator) {
        this.flightAvailabilityOrchestrator = flightAvailabilityOrchestrator;
    }

    @KafkaListener(id = "booking-flight-availability", topics = "booking-flight-availability")
    public void accept(Booking booking) {
        logger.info("received {} ", booking.getFlightNumber());
        flightAvailabilityOrchestrator.orchestrate(booking);
    }
}
