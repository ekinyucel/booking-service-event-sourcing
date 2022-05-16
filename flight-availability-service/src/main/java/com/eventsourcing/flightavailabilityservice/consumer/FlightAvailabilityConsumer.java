package com.eventsourcing.flightavailabilityservice.consumer;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.flightavailabilityservice.orchestrator.FlightAvailabilityOrchestrator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FlightAvailabilityConsumer {

    private final FlightAvailabilityOrchestrator flightAvailabilityOrchestrator;

    public FlightAvailabilityConsumer(FlightAvailabilityOrchestrator flightAvailabilityOrchestrator) {
        this.flightAvailabilityOrchestrator = flightAvailabilityOrchestrator;
    }

    @KafkaListener(id = "booking-flight-availability", topics = "booking-flight-availability")
    public void accept(Booking booking) {
        System.out.println("received " + booking.getFlightNumber());
        flightAvailabilityOrchestrator.orchestrate(booking);
    }
}
