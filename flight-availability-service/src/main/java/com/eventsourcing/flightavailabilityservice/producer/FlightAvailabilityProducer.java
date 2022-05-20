package com.eventsourcing.flightavailabilityservice.producer;

import com.eventsourcing.bookingservice.model.Booking;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class FlightAvailabilityProducer {

    private final KafkaTemplate<String, Booking> kafkaTemplate;

    public FlightAvailabilityProducer(KafkaTemplate<String, Booking> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceEvent(Booking booking) {
        kafkaTemplate.send("flight-availability-result", booking);
    }

}
