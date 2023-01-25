package com.ftn.isa4.dto;

import com.ftn.isa4.model.Appointment;

import java.time.Instant;

public class AppointmentResponse {
    private final Long id;
    private final Long centerId;
    private final Instant start;
    private final Instant end;
    private final String type;

    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.centerId = appointment.getCenter().getId();
        this.start = appointment.getStart();
        this.end = appointment.getEnd();
        this.type = appointment.getType().toString();
    }

    public Long getId() {
        return id;
    }

    public Long getCenterId() {
        return centerId;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public String getType() {
        return type;
    }
}
