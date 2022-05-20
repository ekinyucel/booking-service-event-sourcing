package com.eventsourcing.bookingservice.controller;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.bookingservice.orchestrator.BookingOrchestrator;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookingController {

    private final BookingOrchestrator bookingOrchestrator;
    private StreamsBuilderFactoryBean kafkaStreamsFactory;

    public BookingController(BookingOrchestrator bookingOrchestrator, StreamsBuilderFactoryBean kafkaStreamsFactory) {
        this.bookingOrchestrator = bookingOrchestrator;
        this.kafkaStreamsFactory = kafkaStreamsFactory;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        bookingOrchestrator.orchestrate(booking);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Booking> all() {
        List<Booking> orders = new ArrayList<>();
        KafkaStreams kafkaStreams = kafkaStreamsFactory
                .getKafkaStreams();

        if (null != kafkaStreams) {
            ReadOnlyKeyValueStore<Long, Booking> store = kafkaStreams.store(StoreQueryParameters.fromNameAndType(
                            "booking-flight-availability",
                            QueryableStoreTypes.keyValueStore()));
            KeyValueIterator<Long, Booking> it = store.all();
            it.forEachRemaining(kv -> orders.add(kv.value));
        }
        return orders;
    }

}
