package com.eventsourcing.bookingservice.service;

import com.eventsourcing.bookingservice.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookingPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(BookingPaymentService.class);

    public Booking handlePayment(Booking flightAvailability, Booking bookingAvailability) {
        logger.info("flightAvailability " + flightAvailability.toString() + " bookingAvailability " + bookingAvailability.toString());
        return flightAvailability;
    }

}
