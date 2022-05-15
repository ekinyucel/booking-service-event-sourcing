package com.eventsourcing.flightavailabilityservice.consumer;

import com.eventsourcing.flightavailabilityservice.orchestrator.FlightAvailabilityOrchestrator;
import com.eventsourcing.flightavailabilityservice.model.FlightAvailabilityEvent;
import com.eventsourcing.flightavailabilityservice.model.Statuses;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class FlightAvailabilityConsumer implements Consumer<FlightAvailabilityEvent> {

    private final FlightAvailabilityOrchestrator flightAvailabilityOrchestrator;

    public FlightAvailabilityConsumer(FlightAvailabilityOrchestrator flightAvailabilityOrchestrator) {
        this.flightAvailabilityOrchestrator = flightAvailabilityOrchestrator;
    }


    @Override
    public void accept(FlightAvailabilityEvent flightAvailabilityEvent) {
        System.out.println("received " + flightAvailabilityEvent.getFlightNumber());
        flightAvailabilityOrchestrator.orchestrate(flightAvailabilityEvent);
    }
}
