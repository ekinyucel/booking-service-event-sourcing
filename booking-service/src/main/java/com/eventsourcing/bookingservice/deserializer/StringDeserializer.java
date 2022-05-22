package com.eventsourcing.bookingservice.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Arrays;

public class StringDeserializer  implements Deserializer<String>  {

    @Override
    public String deserialize(String s, byte[] bytes) {
        return Arrays.toString(bytes);
    }
}
