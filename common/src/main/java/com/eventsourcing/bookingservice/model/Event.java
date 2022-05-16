package com.eventsourcing.bookingservice.model;

import java.util.Date;
import java.util.UUID;

public abstract class Event {
    public final String id = UUID.randomUUID().toString();
    public final Date created = new Date();
}
