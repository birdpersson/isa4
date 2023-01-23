package com.ftn.isa4.dto;

import com.ftn.isa4.model.Appointment;

public class AppointmentResponse {
    private final Long id;
    private final Long centerId;
    private final String interval;
    private final String type;

    public AppointmentResponse(Appointment appointment) {
        this.id = appointment.getId();
        this.centerId = appointment.getCenter().getId();
        this.interval = appointment.getInterval().toString();
        this.type = appointment.getType().toString();
    }

    public Long getId() {
        return id;
    }

    public Long getCenterId() {
        return centerId;
    }

    public String getInterval() {
        return interval;
    }

    public String getType() {
        return type;
    }
}
