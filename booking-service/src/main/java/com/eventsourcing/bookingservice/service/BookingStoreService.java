package com.eventsourcing.bookingservice.service;

import com.eventsourcing.bookingservice.model.Booking;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BookingStoreService {

    private final StreamsBuilderFactoryBean kafkaStreamsFactory;

    public BookingStoreService(StreamsBuilderFactoryBean kafkaStreamsFactory) {
        this.kafkaStreamsFactory = kafkaStreamsFactory;
    }

    public List<Booking> fetchAllEvents() {
        List<Booking> bookings = new ArrayList<>();
        KafkaStreams kafkaStreams = kafkaStreamsFactory
                .getKafkaStreams();

        if (null != kafkaStreams) {
            ReadOnlyKeyValueStore<Long, Booking> store = kafkaStreams.store(StoreQueryParameters.fromNameAndType("bookings", QueryableStoreTypes.keyValueStore()));
            KeyValueIterator<Long, Booking> it = store.all();
            it.forEachRemaining(kv -> bookings.add(kv.value));
        }
        bookings.sort(Comparator.comparing(Booking::getCreated).reversed());
        return bookings;
    }

}
