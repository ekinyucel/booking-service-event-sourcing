package com.eventsourcing.flightavailabilityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class FlightAvailabilityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightAvailabilityServiceApplication.class, args);
    }

}
