package com.eventsourcing.bookingservice.service;

import com.eventsourcing.bookingservice.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookingPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(BookingPaymentService.class);

    public void handlePayment(String key, Booking booking) {
        logger.info("key " + key + " " + booking.toString());
    }

}
