package com.eventsourcing.flightavailabilityservice.deserializer;

import com.eventsourcing.flightavailabilityservice.model.FlightAvailability;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class FlightAvailabilityDeSerializer implements Deserializer<FlightAvailability> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public FlightAvailability deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(new String(data), FlightAvailability.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
