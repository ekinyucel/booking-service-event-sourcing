package com.eventsourcing.bookingservice.model;

import java.util.Objects;

public class FlightAvailabilityEvent extends Event {
    private String flightNumber;
    private String origin;
    private String destination;
    private String status;

    public FlightAvailabilityEvent(String flightNumber, String origin, String destination) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.setStatus(Statuses.FLIGHT_PENDING.name());
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals(Statuses.FLIGHT_PENDING.name())
                || status.equals(Statuses.FLIGHT_AVAILABLE.name())
                || status.equals(Statuses.FLIGHT_NOT_AVAILABLE.name())) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("It is not possible to set the flight availability event's status to " + status);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightAvailabilityEvent that = (FlightAvailabilityEvent) o;
        return flightNumber.equals(that.flightNumber) && origin.equals(that.origin) && destination.equals(that.destination) && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, origin, destination, status);
    }

    @Override
    public String toString() {
        return "FlightAvailabilityEvent{" +
                "id=" + id +
                ", created=" + created +
                ", flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
