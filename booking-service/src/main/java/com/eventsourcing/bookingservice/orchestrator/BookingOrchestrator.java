package com.eventsourcing.bookingservice.orchestrator;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.bookingservice.model.BookingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookingOrchestrator {

    private static final Logger logger = LoggerFactory.getLogger(BookingOrchestrator.class);
    private final KafkaTemplate<String, Booking> kafkaTemplate;

    public BookingOrchestrator(KafkaTemplate<String, Booking> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookingResponse orchestrate(Booking booking) {
        logger.info("Creating an event for the flight availability service");
        kafkaTemplate.send("booking-flight-availability", booking.id, booking);
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setId(booking.id);
        return bookingResponse;
    }
}
