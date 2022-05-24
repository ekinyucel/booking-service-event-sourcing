package com.eventsourcing.bookingservice.model;

public enum Statuses {
    FLIGHT_PENDING, FLIGHT_AVAILABLE, FLIGHT_NOT_AVAILABLE, PAYMENT_REQUESTED, PAYMENT_APPROVED, PAYMENT_DECLINED;
}
