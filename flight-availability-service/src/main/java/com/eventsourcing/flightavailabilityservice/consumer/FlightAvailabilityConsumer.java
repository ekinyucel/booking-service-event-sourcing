package com.eventsourcing.flightavailabilityservice.consumer;

import com.eventsourcing.flightavailabilityservice.model.FlightAvailability;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class FlightAvailabilityConsumer implements Consumer<FlightAvailability> {


    @Override
    public void accept(FlightAvailability flightAvailability) {
        System.out.println("received " + flightAvailability.getFlightNumber());
    }
}
