package com.eventsourcing.bookingservice.deserializer;

import com.eventsourcing.bookingservice.model.Booking;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class MessageDeserializer implements Deserializer<Booking> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Booking deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(new String(data), Booking.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}