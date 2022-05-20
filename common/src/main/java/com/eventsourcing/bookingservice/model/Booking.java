package com.eventsourcing.bookingservice.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Booking {
    public String id = UUID.randomUUID().toString();
    public final Date created = new Date();
    private String flightNumber;
    private String origin;
    private String destination;
    private String status = Statuses.FLIGHT_PENDING.name();

    public Booking() {}

    public Booking(String flightNumber, String origin, String destination) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
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
        Booking that = (Booking) o;
        return id.equals(that.id) && flightNumber.equals(that.flightNumber) && origin.equals(that.origin) && destination.equals(that.destination) && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, origin, destination, status);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", created=" + created +
                ", flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}