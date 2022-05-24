package com.eventsourcing.bookingservice.configuration;

import com.eventsourcing.bookingservice.model.Booking;
import org.apache.kafka.clients.admin.NewTopic;
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
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class BookingServiceConfiguration {

    private static final String BOOKINGS = "bookings";
    private static final String BOOKING_FLIGHT_AVAILABILITY = "booking-flight-availability";
    private static final String FLIGHT_AVAILABILITY_RESULT = "flight-availability-result";
    private static final String BOOKING_PAYMENT_ISSUE = "booking-payment-issue";

    @Bean
    public NewTopic bookings() {
        return TopicBuilder.name(BOOKINGS)
                .partitions(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic bookingFlightAvailability() {
        return TopicBuilder.name(BOOKING_FLIGHT_AVAILABILITY)
                .partitions(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic flightAvailabilityResultTopic() {
        return TopicBuilder.name(FLIGHT_AVAILABILITY_RESULT)
                .partitions(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic bookingPaymentAvailability() {
        return TopicBuilder.name(BOOKING_PAYMENT_ISSUE)
                .partitions(1)
                .compact()
                .build();
    }

    @Bean
    public KStream<String, Booking> flightAvailabilityStream(StreamsBuilder builder) {
        JsonSerde<Booking> bookingSerde = new JsonSerde<>(Booking.class);
        KStream<String, Booking> stream = builder.stream(FLIGHT_AVAILABILITY_RESULT, Consumed.with(Serdes.String(), bookingSerde));
        stream.to(BOOKINGS);
        return stream;
    }

    @Bean
    public KTable<String, Booking> table(StreamsBuilder builder) {
        KeyValueBytesStoreSupplier store = Stores.persistentKeyValueStore(BOOKINGS);
        JsonSerde<Booking> bookingSerde = new JsonSerde<>(Booking.class);
        KStream<String, Booking> stream = builder.stream(BOOKINGS, Consumed.with(Serdes.String(), bookingSerde));
        return stream.toTable(Materialized.<String,Booking>as(store)
                .withKeySerde(Serdes.String())
                .withValueSerde(bookingSerde));
    }

}
