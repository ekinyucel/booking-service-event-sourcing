package com.eventsourcing.bookingservice.orchestrator;

import com.eventsourcing.bookingservice.model.FlightAvailabilityEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class BookingOrchestrator {

    private final StreamBridge streamBridge;

    public BookingOrchestrator(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void orchestrate() {
        FlightAvailabilityEvent flightAvailabilityEvent = new FlightAvailabilityEvent("KL1523", "AMS", "IST");
        streamBridge.send("booking-flight-availability-out-0", flightAvailabilityEvent);
    }

}
