package com.eventsourcing.flightavailabilityservice.deserializer;

import com.eventsourcing.flightavailabilityservice.model.FlightAvailabilityEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class FlightAvailabilityDeSerializer implements Deserializer<FlightAvailabilityEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public FlightAvailabilityEvent deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(new String(data), FlightAvailabilityEvent.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
