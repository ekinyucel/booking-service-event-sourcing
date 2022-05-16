package com.eventsourcing.bookingservice.controller;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.bookingservice.orchestrator.BookingOrchestrator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BookingController {

    private final BookingOrchestrator bookingOrchestrator;

    public BookingController(BookingOrchestrator bookingOrchestrator) {
        this.bookingOrchestrator = bookingOrchestrator;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        bookingOrchestrator.orchestrate(booking);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
