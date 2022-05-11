package com.eventsourcing.bookingservice.controller;

import com.eventsourcing.bookingservice.model.FlightAvailability;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.OffsetDateTime;

@Controller
public class BookingController {

    private final StreamBridge streamBridge;

    public BookingController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @GetMapping("/createBooking")
    public ResponseEntity<String> createBooking() {
        FlightAvailability flightAvailability = new FlightAvailability();
        flightAvailability.setFlightNumber("KL1523");
        flightAvailability.setOrigin("AMS");
        flightAvailability.setDestination("IST");
        flightAvailability.setMessageTimestamp(OffsetDateTime.now().toString());
        streamBridge.send("booking-flight-availability-out-0", flightAvailability);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
