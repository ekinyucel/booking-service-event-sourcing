package com.eventsourcing.bookingservice.orchestrator;

import com.eventsourcing.bookingservice.model.Booking;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookingOrchestrator {

    private final KafkaTemplate<String, Booking> kafkaTemplate;

    public BookingOrchestrator(KafkaTemplate<String, Booking> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void orchestrate(Booking booking) {
        kafkaTemplate.send("booking-flight-availability", booking.id, booking);
    }

}
