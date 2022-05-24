package com.eventsourcing.bookingservice.controller;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.bookingservice.model.BookingResponse;
import com.eventsourcing.bookingservice.orchestrator.BookingOrchestrator;
import com.eventsourcing.bookingservice.service.BookingStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {

    private final BookingOrchestrator bookingOrchestrator;
    private final BookingStoreService bookingStoreService;

    public BookingController(BookingOrchestrator bookingOrchestrator, BookingStoreService bookingStoreService) {
        this.bookingOrchestrator = bookingOrchestrator;
        this.bookingStoreService = bookingStoreService;
    }

    @PostMapping("/createBooking")
    public BookingResponse createBooking(@RequestBody Booking aBooking) {
        return bookingOrchestrator.orchestrate(aBooking);
    }

    @GetMapping("/all")
    public List<Booking> all() {
        return bookingStoreService.fetchAllEvents();
    }

}
