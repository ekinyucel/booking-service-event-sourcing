package com.eventsourcing.bookingservice.consumer;

import com.eventsourcing.bookingservice.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookingFlightAvailabilityConsumer {

    private final Logger logger = LoggerFactory.getLogger(BookingFlightAvailabilityConsumer.class);

    @KafkaListener(id = "flight-availability-result", topics = "flight-availability-result")
    public void accept(Booking booking) {
        logger.info("received {} + {}", booking.getFlightNumber(), booking.getStatus());
    }

}
