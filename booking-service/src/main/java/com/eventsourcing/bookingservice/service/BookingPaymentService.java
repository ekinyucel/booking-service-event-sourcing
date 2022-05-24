package com.eventsourcing.bookingservice.service;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.bookingservice.model.Statuses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(BookingPaymentService.class);
    private final KafkaTemplate<String, Booking> kafkaTemplate;

    public BookingPaymentService(KafkaTemplate<String, Booking> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(id = "bookings", topics = "bookings")
    public void accept(Booking booking) {
        if (Statuses.FLIGHT_AVAILABLE.name().equals(booking.getStatus())) {
            logger.info("handle payment for {}", booking);
            booking.setStatus(Statuses.PAYMENT_REQUESTED.name());
            sendIssuePaymentEvent(booking);
        } else {
            logger.info("no free seat is available for {}", booking.getFlightNumber());
        }
    }

    private void sendIssuePaymentEvent(Booking booking) {
        kafkaTemplate.send("booking-payment-issue", booking.id, booking);
    }

}
