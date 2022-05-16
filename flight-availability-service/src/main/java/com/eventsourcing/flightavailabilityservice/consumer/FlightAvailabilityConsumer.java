package com.eventsourcing.flightavailabilityservice.consumer;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.flightavailabilityservice.orchestrator.FlightAvailabilityOrchestrator;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class FlightAvailabilityConsumer implements Consumer<Booking> {

    private final FlightAvailabilityOrchestrator flightAvailabilityOrchestrator;

    public FlightAvailabilityConsumer(FlightAvailabilityOrchestrator flightAvailabilityOrchestrator) {
        this.flightAvailabilityOrchestrator = flightAvailabilityOrchestrator;
    }


    @Override
    public void accept(Booking booking) {
        System.out.println("received " + booking.getFlightNumber());
        flightAvailabilityOrchestrator.orchestrate(booking);
    }
}
