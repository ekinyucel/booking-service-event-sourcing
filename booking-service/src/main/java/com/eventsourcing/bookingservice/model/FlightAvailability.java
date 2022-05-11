package com.eventsourcing.bookingservice.model;

import java.util.Objects;

public class FlightAvailability {
    private String flightNumber;
    private String origin;
    private String destination;
    private String messageTimestamp;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(String messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightAvailability that = (FlightAvailability) o;
        return flightNumber.equals(that.flightNumber) && origin.equals(that.origin) && destination.equals(that.destination) && messageTimestamp.equals(that.messageTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, origin, destination, messageTimestamp);
    }
}
