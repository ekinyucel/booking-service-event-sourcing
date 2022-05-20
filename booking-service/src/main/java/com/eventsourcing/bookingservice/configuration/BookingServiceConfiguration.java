package com.eventsourcing.bookingservice.configuration;

import com.eventsourcing.bookingservice.model.Booking;
import com.eventsourcing.bookingservice.service.BookingPaymentService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class BookingServiceConfiguration {

    private final BookingPaymentService bookingPaymentService;

    public BookingServiceConfiguration(BookingPaymentService bookingPaymentService) {
        this.bookingPaymentService = bookingPaymentService;
    }

    @Bean
    public KStream<String, Booking> stream(StreamsBuilder builder) {
        JsonSerde<Booking> bookingSerde = new JsonSerde<>(Booking.class);
        KStream<String, Booking> stream = builder.stream("flight-availability-result", Consumed.with(Serdes.String(), bookingSerde));
        stream.foreach((k, v) -> bookingPaymentService.handlePayment(v));
        return stream;
    }

    @Bean
    public KTable<String, Booking> table(StreamsBuilder builder) {
        KeyValueBytesStoreSupplier store = Stores.persistentKeyValueStore("bookings");
        JsonSerde<Booking> bookingSerde = new JsonSerde<>(Booking.class);
        KStream<String, Booking> stream = builder.stream("booking-flight-availability", Consumed.with(Serdes.String(), bookingSerde));
        return stream.toTable(Materialized.<String,Booking>as(store)
                .withKeySerde(Serdes.String())
                .withValueSerde(bookingSerde));
    }

}
