package com.eventsourcing.bookingservice.controller;

import com.eventsourcing.bookingservice.orchestrator.BookingOrchestrator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    private final BookingOrchestrator bookingOrchestrator;

    public BookingController(BookingOrchestrator bookingOrchestrator) {
        this.bookingOrchestrator = bookingOrchestrator;
    }

    // TODO this will be a post endpoint
    @GetMapping("/createBooking")
    public ResponseEntity<String> createBooking() {
        bookingOrchestrator.orchestrate();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
